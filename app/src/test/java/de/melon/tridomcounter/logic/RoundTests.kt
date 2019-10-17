package de.melon.tridomcounter.logic

import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RoundTests {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    @Test
    @Before
    fun t00_instantiate() {
        val session = Session(players)
        val firstPlayer = 0
        round = Round(session, firstPlayer)

    }

    @Test
    fun t01_makeSimpleMove() {
        assertCurrentPlayer(0)
        round.makeMove(BaseMove)
        assertCurrentPlayer(1)

    }

    @Test
    fun t02_playFullRound() {
        assertCurrentPlayer(0)
        round.makeMove(BaseMove)
        assertCurrentPlayer(1)
        round.makeMove(BaseMove)
        assertCurrentPlayer(2)

        round.makeMove(BaseMove)
        assertCurrentPlayer(0)


    }

    val move3 = PlaceMove(BaseMove, 3)
    val move10 = PlaceMove(BaseMove, 10)
    val moveN4 = PlaceMove(BaseMove, -4)

    @Test
    fun t03_playerPointsSum() {
        assertCurrentPlayer(0)
        round.makeMove(move10)
        assertPlayerPoints(0, 10)

        assertCurrentPlayer(1)
        round.makeMove(moveN4)
        assertPlayerPoints(1, -4)

        assertCurrentPlayer(2)
        round.makeMove(move3)
        assertPlayerPoints(2, 3)

        assertCurrentPlayer(0)
        round.makeMove(moveN4)
        assertPlayerPoints(0, 6)

        assertCurrentPlayer(1)
        round.makeMove(move3)
        assertPlayerPoints(1, -1)

        assertCurrentPlayer(2)
        round.makeMove(move10)
        assertPlayerPoints(2, 13)

    }

    fun assertCurrentPlayer(id: Int) = assert(round.currentPlayerId == id)

    fun assertPlayerPoints(id: Int, points: Int) = assert(round.getPoints(id) == points)

}