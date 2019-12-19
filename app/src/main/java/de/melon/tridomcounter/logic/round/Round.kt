package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.PointInterface
import de.melon.tridomcounter.logic.Session
import kotlin.properties.Delegates

class Round(val session: Session) : PointInterface {
    var state by Delegates.observable(RoundState.CHOOSE_VARIANT) {
        _, _, _ ->
        updateCards()
    }

    val cards = mutableListOf<Card>()

    init { updateCards() }

    private fun updateCards() {
        cards.clear()

        when (state) {
            RoundState.CHOOSE_VARIANT -> {
                cards.add(ActionCardSimple("Tridom", ::chooseNormalVariant))
                cards.add(ActionCardSimple("Super Tridom", ::chooseSuperVariant))
                cards.add(ActionCardComplex("Custom Tridom", ::chooseCustomVariant))

            }

            RoundState.CHOOSE_FIRST_PLAYER -> {
                for (player in session.players)
                    cards.add(ActionCardChoice(player,
                                ::choosePlayer))

            }

            RoundState.FIRST_MOVE -> {
                for (i in 0 until 6)
                    cards.add(ActionCardChoice("Triple $i",
                                ::chooseTriple))

                cards.add(ActionCardComplex("Anderer Stein",
                            ::chooseCustomFirstPiece))

            }

            RoundState.NORMAL -> {
                if (currentMove.ableToPlace())
                    cards.add(ActionCardComplex("Legen", ::place))

                if (currentMove.ableToDraw())
                    cards.add(ActionCardSimple("Ziehen", ::draw))

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

    private var currentMove : AbstractMove by Delegates.observable(StartMove) {
            _, _ : AbstractMove, _: AbstractMove ->
            updateCards()
    }

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

        state = RoundState.FIRST_MOVE

    }

    // FIRST_MOVE

    fun chooseTriple(i: Int) {
        val points = when (i) {
            0 -> 40
            else -> 3 * i
        }

        chooseCustomFirstPiece(points)

    }

    fun chooseCustomFirstPiece(points: Int) {
        currentMove = Move(currentMove, points)
        next()

        state = RoundState.NORMAL

    }

    // NORMAL

    private fun pass() {
        if (currentMove.ableToPlace())
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

    private fun draw() {
        currentMove = DrawMove(currentMove)

    }

    private fun place(points: Int) {
        currentMove = Move(currentMove, points)

    }

    // LAST_MOVE


}