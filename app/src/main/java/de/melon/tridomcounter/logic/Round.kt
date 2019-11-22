package de.melon.tridomcounter.logic

import kotlin.properties.Delegates

class Round(val session: Session, var currentPlayerId: Int) : PointInterface {
    private var currentMove : AbstractMove by Delegates.observable(BaseMove) {
            _, _ : AbstractMove, _: AbstractMove ->
            updateActions()
    }

    val moves = Array(session.numberOfPlayers) {MutableList<AbstractMove>(0) {BaseMove}}

    override fun getPoints(id: Int) = moves[id].sumBy { m -> m.points } + currentMove.points

    private fun draw() {
        currentMove = DrawMove(currentMove)

    }

    private fun placeMove(points: Int) {
        currentMove = Move(currentMove, points)
        finishMove()

    }

    private fun finishMove() {
        moves[currentPlayerId].add(currentMove)

        currentPlayerId++

        if (currentPlayerId >= session.numberOfPlayers)
            currentPlayerId = 0

        currentMove = BaseMove

    }

    private fun drawMove() = draw()
    private fun passMove() = placeMove(-10)

    val roundActions = RoundActions()

    init {
        updateActions()
    }

    private fun updateActions() {
        roundActions.clear()

        roundActions.addComplexMove(::placeMove, "Legen")

        if (currentMove.isOver())
            roundActions.addSimpleMove(::passMove, "Weiter")

        else
            roundActions.addSimpleMove(::drawMove, "Ziehen")

    }

}