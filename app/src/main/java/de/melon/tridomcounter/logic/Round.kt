package de.melon.tridomcounter.logic

import kotlin.properties.Delegates

class Round(val session: Session, var currentPlayerId: Int) : PointInterface {
    private var currentMove : AbstractMove by Delegates.observable(BaseMove) {
            _, _ : AbstractMove, _: AbstractMove ->
            updateActionLists()
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

    val customMoveList = MutableList(0) {Pair<(Int) -> Unit, String>(::placeMove, "")}

    val commonMoveList = MutableList(0) {Pair<() -> Unit, String>(::drawMove, "")}

    init {
        updateActionLists()
    }

    private fun drawMove() = draw()
    private fun passMove() = placeMove(-10)

    private fun updateActionLists() {
        customMoveList.clear()
        customMoveList.add(Pair(::placeMove, "Legen"))

        commonMoveList.clear()
        commonMoveList.add(Pair(::drawMove, "Ziehen"))
        commonMoveList.add(Pair(::passMove, "Zo oft gezogen"))

    }

}