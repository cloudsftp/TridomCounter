package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.Session
import org.junit.Before
import org.junit.Test

class RoundTestsChooseVariant {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    @Test
    @Before
    fun instantiate() {
        val session = Session(players)
        round = Round(session)

        for (i in players.indices)
            checkPoints(round, i, 0)

    }

    @Test
    fun testNormalVariant() = chooseTridomVariant(round, 0)

    @Test
    fun testSuperVariant() = chooseTridomVariant(round, 1)

    @Test
    fun testCustomVariant() = chooseCustomTridomVariant(round, 20)

}
