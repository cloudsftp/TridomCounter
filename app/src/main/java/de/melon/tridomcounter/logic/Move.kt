package de.melon.tridomcounter.logic

abstract class AbstractMove {
    open val actions = 0
    open val points = 0

    val maxActions = 3
    fun isOver() = actions >= maxActions

}

object BaseMove : AbstractMove()

open class WrapperMove(innerMove: AbstractMove, deltaPoints: Int, deltaActions: Int) : AbstractMove() {
    override val actions = innerMove.actions + deltaActions
    override val points = innerMove.points + deltaPoints

}

class Move(innerMove: AbstractMove, deltaPoints: Int) : WrapperMove(innerMove, deltaPoints, 1)

class DrawMove(innerMove: AbstractMove) : WrapperMove(innerMove, -5, 1)
