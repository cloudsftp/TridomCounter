package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.PointInterface
import de.melon.tridomcounter.logic.Session
import kotlin.properties.Delegates

class Round(val session: Session) :
    PointInterface {
    private var currentMove : AbstractMove by Delegates.observable(
        BaseMove
    ) {
            _, _ : AbstractMove, _: AbstractMove ->
            updateCards()
    }

    var currentPlayerId = -1
    val moves = Array(session.numberOfPlayers) {MutableList<AbstractMove>(0) { BaseMove }}

    override fun getPoints(id: Int) = moves[id].sumBy { m -> m.points } + currentMove.points

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

    val cards = mutableListOf<Card>()

    init {
        currentMove = StartMove

        updateCards()
    }

    private fun updateCards() {
        cards.clear()

        cards.add(ActionCardSimple("Weiter", ::pass))

        if (currentMove.ableToPlace())
            cards.add(ActionCardComplex("Legen", ::place))

        if (currentMove.ableToDraw())
            cards.add(ActionCardSimple("Ziehen", ::draw))

        var move: AbstractMove = currentMove

        while (move is WrapperMove) {
            cards.add(DisplayCard(move.deltaPoints.toString()))
            move = move.innerMove

        }

    }

}