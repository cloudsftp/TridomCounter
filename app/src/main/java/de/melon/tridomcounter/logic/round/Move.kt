package de.melon.tridomcounter.logic.round

abstract class AbstractMove {
    open val points = 0
    open val pieces = 0

    open val drawActions = 0
    private val maxDrawActions = 3
    fun ableToDraw() = drawActions < maxDrawActions

}

object BaseMove : AbstractMove()

object StartMove : WrapperMove(BaseMove, 20, 0, 0)

open class WrapperMove(val innerMove: AbstractMove, val deltaPoints: Int,
                       deltaDrawActions: Int, deltaPieces: Int)
        : AbstractMove() {
    override val points = innerMove.points + deltaPoints
    override val pieces = innerMove.pieces + deltaPieces
    override val drawActions = innerMove.drawActions + deltaDrawActions

}

class Move(innerMove: AbstractMove, deltaPoints: Int)
    : WrapperMove(innerMove, deltaPoints, 0, -1)

class DrawMove(innerMove: AbstractMove)
    : WrapperMove(innerMove, -5, 1, 1)

open class VirtualMove(innerMove: AbstractMove, points: Int)
    : WrapperMove(innerMove, points, 0, 0)

class PassMove(innerMove: AbstractMove)
    : VirtualMove(innerMove, -10)

class WinBonusMove(innerMove: AbstractMove)
    : VirtualMove(innerMove, 25)

class BonusMove(innerMove: AbstractMove, points: Int)
    : VirtualMove(innerMove, points)
