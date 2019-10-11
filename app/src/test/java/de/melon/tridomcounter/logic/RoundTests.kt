package de.melon.tridomcounter.logic

import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RoundTests {

    val players = arrayOf("Fabian", "Paul", "Tim")

    @Test
    @Before
    fun t00_instantiate() {
        val session = Session(players)
        val firstPlayer = 0
        val round = Round(session, firstPlayer)

    }

    @Test
    fun t01_makeSimpleMove() {


    }

}