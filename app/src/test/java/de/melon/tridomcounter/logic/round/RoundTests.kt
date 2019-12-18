package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.Session
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class RoundTests {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 1

    @Test
    @Before
    fun instantiate() {
        val session = Session(players)
        round = Round(session)

    }

}
