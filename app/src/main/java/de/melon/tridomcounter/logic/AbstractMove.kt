package de.melon.tridomcounter.logic

abstract class AbstractMove {
    open val numberOfActions = 0
    open val points = 0

    val maxNumberOfActions = 3
    fun isFinished() = numberOfActions >= maxNumberOfActions

}

object BaseMove : AbstractMove()

abstract class WrapperMove(innerMove: AbstractMove, deltaPoints: Int, deltaActions: Int) : AbstractMove() {
    override val numberOfActions = innerMove.numberOfActions + deltaActions
    override val points = innerMove.points + deltaPoints

}

class Move(innerMove: AbstractMove, deltaPoints: Int) : WrapperMove(innerMove, deltaPoints, 0)

class DrawMove(innerMove: AbstractMove) : WrapperMove(innerMove, -5, 1)
