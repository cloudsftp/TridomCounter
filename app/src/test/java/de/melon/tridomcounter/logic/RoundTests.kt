package de.melon.tridomcounter.logic

import org.junit.Before
import org.junit.Test

class RoundTests {

    companion object {
        lateinit var round: Round

    }

    @Test
    @Before
    fun t01_instantiate() {
        val session = Session(0)
        round = Round(session)

    }

}