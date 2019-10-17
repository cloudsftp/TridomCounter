package de.melon.tridomcounter.logic

abstract class Move {
    open val numberOfActions = 0
    open val points = 0

    val maxNumberOfActions = 3
    fun isFinished() = numberOfActions >= maxNumberOfActions

}

object BaseMove : Move()

abstract class WrapperMove(innerMove: Move, deltaPoints: Int, deltaActions: Int) : Move() {
    override val numberOfActions = innerMove.numberOfActions + deltaActions
    override val points = innerMove.points + deltaPoints

}

class PlaceMove(innerMove: Move, deltaPoints: Int) : WrapperMove(innerMove, deltaPoints, 0)

class DrawMove(innerMove: Move) : WrapperMove(innerMove, -5, 1)
