package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.round.AbstractMove
import de.melon.tridomcounter.logic.round.BaseMove
import de.melon.tridomcounter.logic.round.DrawMove
import de.melon.tridomcounter.logic.round.Move
import org.junit.Assert.assertFalse
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MoveTests {

    // replace function test WrapperMove

    @Test
    fun t02_instantiateDrawMove() {
        val move =
            DrawMove(BaseMove)

        assert(move.points == -5)
        assert(move.drawActions == 1)

    }

    @Test
    fun t03_instantiateMove() {
        val move =
            Move(BaseMove, 10)

        assert(move.points == 10)
        assert(move.placeActions == 1)

    }

    @Test
    fun t04_drawMoveAndMove() {
        var move : AbstractMove =
            BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = Move(move, 20)

        assert(move.points == 10)
        assert(move.drawActions == 2)
        assert(move.placeActions == 1)

    }

    @Test
    fun t05_isOver() {
        var move : AbstractMove =
            BaseMove

        move = DrawMove(move)
        move = DrawMove(move)
        move = DrawMove(move)

        assertFalse(move.ableToDraw())

    }

}