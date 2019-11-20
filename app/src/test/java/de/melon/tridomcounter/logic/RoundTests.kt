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
        // TODO replace

    }

    @Test
    fun t02_playFullRound() {
        // TODO replace

    }

    @Test
    fun t03_playerPointsSum() {
        // TODO replace


    }

}