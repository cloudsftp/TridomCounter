package de.melon.tridomcounter.logic.round

import android.content.Context
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.logic.PointInterface
import de.melon.tridomcounter.logic.Session

class Round(val session: Session) : PointInterface {
    lateinit var context: Context
    fun string(id: Int) = context.getString(id)

    fun title() : String {
        val titleBuilder = StringBuilder(context.getString(R.string.round))
        titleBuilder.append(' ')
        titleBuilder.append(current.roundId + 1)
        titleBuilder.append(" - ")
        titleBuilder.append(
            when (state) {
                RoundState.CHOOSE_VARIANT -> context.getString(R.string.choose_variant)
                RoundState.CHOOSE_FIRST_PLAYER -> context.getString(R.string.choose_first_player)
                RoundState.FIRST_MOVE -> context.getString(R.string.choose_first_piece)
                RoundState.NORMAL -> session.players[currentPlayerId]
                RoundState.LAST_MOVE -> session.players[currentPlayerId]
            }
        )

        updateCards()

        return titleBuilder.toString()

    }

    var state = RoundState.CHOOSE_VARIANT

    val cards = mutableListOf<Card>()

    private fun updateCards() {
        cards.clear()

        when (state) {
            RoundState.CHOOSE_VARIANT -> {
                cards.add(ActionCardSimple(string(R.string.tridom), ::chooseNormalVariant))
                cards.add(ActionCardSimple(string(R.string.super_tridom), ::chooseSuperVariant))
                cards.add(ActionCardComplex(string(R.string.custom_tridom), ::chooseCustomVariant))

            }

            RoundState.CHOOSE_FIRST_PLAYER -> {
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

                if (currentMove.ableToDraw())
                    cards.add(ActionCardSimple(string(R.string.draw), ::draw))

                else
                    cards.add(ActionCardSimple(string(R.string.pass), ::pass))

                var move
                        : AbstractMove = currentMove

                while (move is WrapperMove) {
                    cards.add(DisplayCard(move.deltaPoints.toString()))
                    move = move.innerMove

                }

            }

            RoundState.LAST_MOVE -> {

            }

        }

    }

    private var currentMove : AbstractMove = BaseMove

    var currentPlayerId = -1
    val moves = Array(session.numberOfPlayers) {MutableList<AbstractMove>(0) { BaseMove }}

    override fun getPoints(id: Int) = moves[id].sumBy { m -> m.points } + currentMove.points


    // CHOOSE_VARIANT

    var numOfPieces = -1

    private fun chooseNormalVariant() = chooseCustomVariant(56)
    private fun chooseSuperVariant() = chooseCustomVariant(76)
    private fun chooseCustomVariant(pieces: Int) {
        numOfPieces = pieces

        state = RoundState.CHOOSE_FIRST_PLAYER

    }

    // CHOOSE_FIRST_PLAYER

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

        next()

    }

    private fun draw() {
        currentMove = DrawMove(currentMove)

    }

    private fun pass() {
        currentMove = PassMove(currentMove)

        next()

    }

    private fun next() {
        moves[currentPlayerId].add(currentMove)

        currentPlayerId++

        if (currentPlayerId >= session.numberOfPlayers)
            currentPlayerId = 0

        currentMove = BaseMove

    }

    // LAST_MOVE


}