package de.melon.tridomcounter.logic

import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RoundTests {

    companion object {
        lateinit var round: Round

    }

    val players = arrayOf("Fabian", "Paul", "Tim")

    @Test
    @Before
    fun t01_instantiate() {
        val session = Session(players)
        round = Round(session)

    }

}