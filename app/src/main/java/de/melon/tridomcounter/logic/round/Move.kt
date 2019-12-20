package de.melon.tridomcounter.logic.round

abstract class AbstractMove {
    open val points = 0

    open val drawActions = 0
    private val maxDrawActions = 3
    fun ableToDraw() = drawActions < maxDrawActions

}

object BaseMove : AbstractMove()

object StartMove : WrapperMove(BaseMove, 20, 0)

open class WrapperMove(val innerMove: AbstractMove, val deltaPoints: Int,
                       deltaDrawActions: Int)
        : AbstractMove() {
    override val points = innerMove.points + deltaPoints
    override val drawActions = innerMove.drawActions + deltaDrawActions

}

class Move(innerMove: AbstractMove, deltaPoints: Int)
    : WrapperMove(innerMove, deltaPoints, 0)

class DrawMove(innerMove: AbstractMove)
    : WrapperMove(innerMove, -5, 1)

class PassMove(innerMove: AbstractMove)
    : WrapperMove(innerMove, -10, 0)
