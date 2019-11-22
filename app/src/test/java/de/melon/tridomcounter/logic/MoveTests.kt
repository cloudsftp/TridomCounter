package de.melon.tridomcounter.logic

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MoveTests {

    @Test
    fun t01_instantiateWrapperMove() {
        var move = WrapperMove(BaseMove, 1, 1)
        assert(move.points == 1)
        assert(move.actions == 1)

        move = WrapperMove(move, -1, 1)
        assert(move.points == 0)
        assert(move.actions == 2)

    }

    @Test
    fun t02_instantiateDrawMove() {
        val move = DrawMove(BaseMove)

        assert(move.points == -5)
        assert(move.actions == 1)

    }

    @Test
    fun t03_instantiateMove() {
        val move = Move(BaseMove, 10)

        assert(move.points == 10)
        assert(move.actions == 1)

    }

    @Test
    fun t04_drawMoveAndMove() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = Move(move, 20)

        assert(move.points == 10)
        assert(move.actions == 3)

    }

    @Test
    fun t05_isOver() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = DrawMove(move)

        assert(move.isOver())

    }

}