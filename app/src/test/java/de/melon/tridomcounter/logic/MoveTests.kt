package de.melon.tridomcounter.logic

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MoveTests {

    @Test
    fun t00_drawThreeTimes() {
        var move: Move = BaseMove

        for (i in 0 until 3)
            move = DrawMove(move)

        assert(move.isFinished())

        assert(move.points == -15)

    }

    @Test
    fun t01_place() {
        var move: Move = BaseMove

        move = PlaceMove(move, 10)

        assert(move.points == 10)

    }

    @Test
    fun t02_drawAndPlace() {
        var move: Move = BaseMove

        move = DrawMove(move)
        move = PlaceMove(move, 10)

        assert(move.points == 5)

    }

}