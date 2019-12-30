package de.melon.tridomcounter.logic.round

import android.content.Context
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.logic.*

class Round(val session: Session) : PointInterface {
    lateinit var context: Context
    fun string(id: Int) = context.getString(id)

    private var state = RoundState.VARIANT

    fun title() : String {
        val titleBuilder = StringBuilder(context.getString(R.string.round))
        titleBuilder.append(' ')
        titleBuilder.append(current.roundId + 1)
        titleBuilder.append(" - ")
        titleBuilder.append(
            when (state) {
                RoundState.VARIANT -> context.getString(R.string.choose_variant)
                RoundState.PIECES -> string(R.string.choose_pieces)
                RoundState.FIRST_PLAYER -> context.getString(R.string.choose_first_player)
                RoundState.FIRST_MOVE -> context.getString(R.string.choose_first_piece)
                RoundState.NORMAL -> session.players[currentPlayerId]
                RoundState.WIN -> "${session.players[currentPlayerId]} ${string(R.string.won)}"
                RoundState.STALEMATE -> "${string(R.string.stalemate)} - ${session.players[currentPlayerId]}"
                RoundState.DONE -> string(R.string.done)
            }
        )

        updateCards()

        return titleBuilder.toString()

    }

    fun help() : String {
        return string(when (state) {
            RoundState.VARIANT -> R.string.help_variant
            RoundState.PIECES -> R.string.help_pieces
            RoundState.FIRST_PLAYER -> R.string.help_first_player
            RoundState.FIRST_MOVE -> R.string.help_first_move
            RoundState.NORMAL -> R.string.help_normal
            RoundState.WIN -> R.string.help_win
            RoundState.STALEMATE -> R.string.help_stalemate
            RoundState.DONE -> R.string.help_done
        })

    }

    val cards = mutableListOf<Card>()

    fun updateCards() {
        cards.clear()

        when (state) {
            RoundState.VARIANT -> {
                cards.add(ActionCardSimple(string(R.string.tridom), ::chooseNormalVariant))
                cards.add(ActionCardSimple(string(R.string.super_tridom), ::chooseSuperVariant))
                cards.add(ActionCardComplex(string(R.string.custom_number), ::chooseCustomVariant))

            }

            RoundState.PIECES -> {
                cards.add(ActionCardSimple(string(R.string.seven), ::choose7Pieces))
                cards.add(ActionCardSimple(string(R.string.nine), ::choose9Pieces))
                cards.add(ActionCardComplex(string(R.string.custom_number), ::choosePieces))

            }

            RoundState.FIRST_PLAYER -> {
                for (player in session.players)
                    cards.add(ActionCardChoice(player, ::choosePlayer))

            }

            RoundState.FIRST_MOVE -> {
                for (i in 0 until 6)
                    cards.add(ActionCardChoice(
                        "${string(R.string.triple)} $i",
                        ::chooseTriple))

                cards.add(ActionCardComplex(
                    string(R.string.other_piece), ::chooseCustomFirstPiece)
                )

            }

            RoundState.NORMAL -> {
                cards.add(ActionCardComplex(string(R.string.make_move), ::place))

                if (currentMove.ableToDraw() && getPieces() > 0)
                    cards.add(ActionCardSimple(string(R.string.draw), ::draw))
                else
                    cards.add(ActionCardSimple(string(R.string.pass), ::pass))

            }

            RoundState.WIN -> {
                cards.add(ActionCardSimple(string(R.string.finish), ::finishRound))
                cards.add(ActionCardComplex(string(R.string.add_bonus), ::addBonus))

            }

            RoundState.STALEMATE -> {
                cards.add(ActionCardSimple(string(R.string.continue_punishment), ::continuePunishment))
                cards.add(ActionCardComplex(string(R.string.add_punishment), ::addPunishment))

            }

            RoundState.DONE -> {
                cards.add(DisplayCard(string(R.string.no_more_actions)))

            }

        }

        if (currentMove != BaseMove) {
            var move: AbstractMove = currentMove

            while (move is WrapperMove) {
                cards.add(DisplayCard(move.deltaPoints.toString()))
                move = move.innerMove as AbstractMove

            }

        }

        cards.add(ActionCardSimple(string(R.string.undo), ::undo))

    }

    private var currentMove : AbstractMove = BaseMove

    var currentPlayerId = -1
    val moves = Array(session.numberOfPlayers) {MutableList<AbstractMove>(0) { BaseMove }}

    override fun getPoints(playerId: Int) = currentMove.points + moves[playerId].sumBy { it.points }

    private var numOfPieces = -1
    private fun getPieces()
            = numOfPieces - currentMove.drawActions - moves.sumBy { it.sumBy { it.drawActions } } -
                session.players.size * numOfStartPiecesEach

    private var numOfStartPiecesEach = -1
    private fun getPieces(playerId: Int)
            = numOfStartPiecesEach + currentMove.pieces + moves[playerId].sumBy { it.pieces }

    // undo

    private fun undo() {
        when (state) {
            RoundState.NORMAL -> {
                lastPlayer()
                restoreMove()
            }

        }

    }

    private fun lastPlayer() {
        currentPlayerId -= 1
        if (currentPlayerId < 0)
            currentPlayerId = session.players.indices.last

    }

    private fun restoreMove() {
        val playerMoves = moves[currentPlayerId]
        currentMove = playerMoves.removeAt(playerMoves.indices.last)

        val innerMove = currentMove.innerMove
        when (innerMove) {
            is Move, BaseMove -> currentMove = innerMove
            is StartMove -> {
                currentMove = innerMove
                state = RoundState.FIRST_MOVE
            }
        }

    }

    // VARIANT

    private fun chooseNormalVariant() = chooseCustomVariant(56)
    private fun chooseSuperVariant() = chooseCustomVariant(76)
    private fun chooseCustomVariant(pieces: Int) {
        numOfPieces = pieces

        state = RoundState.PIECES

    }

    // PIECES

    private fun choose7Pieces() = choosePieces(7)
    private fun choose9Pieces() = choosePieces(9)
    private fun choosePieces(pieces: Int) {
        numOfStartPiecesEach = pieces

        state = RoundState.FIRST_PLAYER

    }

    // FIRST_PLAYER

    private fun choosePlayer(playerId: Int) {
        currentPlayerId = playerId
        currentMove = StartMove

        state = RoundState.FIRST_MOVE

    }

    // FIRST_MOVE

    private fun chooseTriple(i: Int) {
        val points = when (i) {
            0 -> 40
            else -> 3 * i
        }

        chooseCustomFirstPiece(points)

    }

    private fun chooseCustomFirstPiece(points: Int) {
        currentMove = Move(currentMove, points)
        next()

        state = RoundState.NORMAL

    }

    // NORMAL

    private fun place(points: Int) {
        currentMove = Move(currentMove, points)

        if (getPieces(currentPlayerId) > 0) {
            next()

        } else {
            currentMove = WinBonusMove(currentMove)

            state = RoundState.WIN

        }

    }

    private fun draw() {
        currentMove = DrawMove(currentMove)

    }

    private fun pass() {
        currentMove = PassMove(currentMove)

        next()

        if (getPieces() == 0 && moves.all { it.last() is PassMove })
            state = RoundState.STALEMATE

    }

    private fun next() {
        saveCurrentMove()

        currentPlayerId++

        if (currentPlayerId >= session.numberOfPlayers)
            currentPlayerId = 0

    }

    // WIN

    fun addBonus(points: Int) {
        currentMove = BonusMove(currentMove, points)

    }

    fun finishRound() {
        saveCurrentMove()

        state = RoundState.DONE

    }

    // STALEMATE

    fun addPunishment(points: Int) {
        currentMove =
            PunishMove(currentMove, points)

    }

    fun continuePunishment() {
        next()

        if (moves.all { it.last() is PunishMove })
            state = RoundState.DONE

    }

    fun saveCurrentMove() {
        moves[currentPlayerId].add(currentMove)
        currentMove = BaseMove

    }

}