package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.Session
import org.junit.Before
import org.junit.Test

class RoundTestsChooseFirstPlayer {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    @Test
    @Before
    fun setUp() {
        val session = Session(players)
        round = Round(session)

        chooseCustomTridomVariant(round, 20)

    }

    @Test
    fun chooseFirstPlayerAsFirstPlayer()
            = chooseFirstPlayer(round, 0, players)

    @Test
    fun chooseSecondPlayerAsFirstPlayer()
            = chooseFirstPlayer(round, 1, players)

    @Test
    fun chooseThirdPlayerAsFirstPlayer()
            = chooseFirstPlayer(round, 2, players)

}
