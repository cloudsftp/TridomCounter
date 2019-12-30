package de.melon.tridomcounter.logic.round

import android.content.Context
import de.melon.tridomcounter.logic.Session
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChooseFirstPlayerTest {

    @Mock
    lateinit var context: Context

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    @Test
    @Before
    fun setUp() {
        val session = Session(players)
        round = Round(session)

        Mockito.doReturn("").`when`(context).getString(Matchers.anyInt())
        round.context = context

        chooseCustomTridomVariant(round, 20)

        choose7Pieces(round)

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

    @Test
    fun undo() {
        chooseUndo(round)

        update(round)
        assertEquals(4, round.cards.size)

    }

}
