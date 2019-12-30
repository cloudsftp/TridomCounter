package de.melon.tridomcounter.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class MoveTest {

    @Test
    fun instantiateDrawMove() {
        val move =
            DrawMove(BaseMove)

        assertMove(move, -5, 1, 1)

    }

    @Test
    fun instantiateMove() {
        val move = Move(
            BaseMove,
            10
        )

        assertMove(move, 10, -1, 0)

    }

    @Test
    fun drawMoveAndMove() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = Move(move, 20)

        assertMove(move, 10, 1, 2)

    }

    @Test
    fun bonusMove() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = Move(move, 15)
        move = BonusMove(move, 20)

        assertMove(move, 30, 0, 1)

        move = BonusMove(move, 50)

        assertMove(move, 80, 0, 1)

    }

    @Test
    fun winBonusMove() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = Move(move, 15)
        move = WinBonusMove(move)

        assertMove(move, 35, 0, 1)

    }

    @Test
    fun punishMove() {
        var move: AbstractMove = BaseMove

        move = DrawMove(move)
        move = Move(move, 15)
        move = PunishMove(move, 10)

        assertMove(move, 0, 0, 1)

    }

    fun assertMove(move: AbstractMove, points: Int, pieces: Int, drawActions: Int) {
        assertEquals(points, move.points)
        assertEquals(pieces, move.pieces)
        assertEquals(drawActions, move.drawActions)

    }

    @Test
    fun isOver() {
        var move : AbstractMove = BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = DrawMove(move)

        assertFalse(move.ableToDraw())

    }

}