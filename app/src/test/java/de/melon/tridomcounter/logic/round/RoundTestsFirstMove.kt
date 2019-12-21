package de.melon.tridomcounter.logic.round

import android.content.Context
import de.melon.tridomcounter.logic.Session
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoundTestsFirstMove {

    @Mock
    lateinit var context: Context

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 0

    @Test
    @Before
    fun setUp() {
        val session = Session(players)
        round = Round(session)

        Mockito.doReturn("").`when`(context).getString(Matchers.anyInt())
        round.context = context

        chooseCustomTridomVariant(round, 20)

        chooseFirstPlayer(round, firstPlayer, players)

    }

    @Test
    fun chooseTriple0()
            = chooseFirstPiece(round, firstPlayer, 0, 60)

    @Test
    fun chooseTriple1()
            = chooseFirstPiece(round, firstPlayer, 1, 23)

    @Test
    fun chooseTriple2()
            = chooseFirstPiece(round, firstPlayer, 2, 26)

    @Test
    fun chooseTriple3()
            = chooseFirstPiece(round, firstPlayer, 3, 29)

    @Test
    fun chooseTriple4()
            = chooseFirstPiece(round, firstPlayer, 4, 32)

    @Test
    fun chooseTriple5()
            = chooseFirstPiece(round, firstPlayer, 5, 35)

    @Test
    fun chooseCustom()
            = chooseCustomFirstPiece(round, firstPlayer, 13)

}
