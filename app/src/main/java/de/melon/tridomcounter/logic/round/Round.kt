package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.PointInterface
import de.melon.tridomcounter.logic.Session
import kotlin.properties.Delegates

class Round(val session: Session) : PointInterface {
    var state = RoundState.CHOOSE_VARIANT

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
                for (playerId in 0 until session.players.size)
                    cards.add(ActionCardChoice(session.players[playerId],
                                ::choosePlayer))

            }

            RoundState.CHOOSE_FIRST_PIECE -> {

            }

            RoundState.FIRST_MOVE -> {

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

    private var currentMove : AbstractMove by Delegates.observable(BaseMove) {
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
        updateCards()

    }

    // CHOOSE_FIRST_PLAYER

    private fun choosePlayer(playerId: Int) {
        currentPlayerId = playerId

        state = RoundState.CHOOSE_FIRST_PIECE

    }

    // CHOOSE_FIRST_PIECE
    

    // NORMAL or FIRST_MOVE

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