package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.Session
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

        for (i in 0 until players.size)
            checkPoints(round, i, 0)

    }

    @Test
    fun testNormalVariant() = chooseTridomVariant(round, 0)

    @Test
    fun testSuperVariant() = chooseTridomVariant(round, 1)

    @Test
    fun testCustomVariant() = chooseCustomTridomVariant(round, 20)

}
