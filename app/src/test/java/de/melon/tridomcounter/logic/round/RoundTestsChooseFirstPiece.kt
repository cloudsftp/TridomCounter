package de.melon.tridomcounter.logic.round

import de.melon.tridomcounter.logic.Session
import org.junit.Before
import org.junit.Test

class RoundTestsChooseFirstPiece {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 0

    @Test
    @Before
    fun setUp() {
        val session = Session(players)
        round = Round(session)

        chooseCustomTridomVariant(round, 20)

        chooseFirstPlayer(round, firstPlayer, players)

    }

    @Test
    fun testChooseTriple0()
            = chooseFirstPiece(round, firstPlayer, 0, 60)

    @Test
    fun testChooseTriple1()
            = chooseFirstPiece(round, firstPlayer, 1, 23)

    @Test
    fun testChooseTriple2()
            = chooseFirstPiece(round, firstPlayer, 2, 26)

    @Test
    fun testChooseTriple3()
            = chooseFirstPiece(round, firstPlayer, 3, 29)

    @Test
    fun testChooseTriple4()
            = chooseFirstPiece(round, firstPlayer, 4, 32)

    @Test
    fun testChooseTriple5()
            = chooseFirstPiece(round, firstPlayer, 5, 35)

    @Test
    fun testChooseCustom()
            = chooseCustomFirstPiece(round, firstPlayer, 13)

}
