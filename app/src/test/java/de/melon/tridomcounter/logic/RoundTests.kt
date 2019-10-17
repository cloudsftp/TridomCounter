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
        assert(round.currentPlayerId == 0)
        round.makeMove(BaseMove)
        assert(round.currentPlayerId == 1)

    }

    @Test
    fun t02_playFullRound() {
        assert(round.currentPlayerId == 0)
        round.makeMove(BaseMove)
        assert(round.currentPlayerId == 1)
        round.makeMove(BaseMove)
        assert(round.currentPlayerId == 2)

        round.makeMove(BaseMove)
        assert(round.currentPlayerId == 0)


    }

}