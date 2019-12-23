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
class ChoosePiecesTest {

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

    }

    @Test
    fun testChoose7Pieces() = choose7Pieces(round)

    @Test
    fun testChoose9Pieces() = choose9Pieces(round)

    @Test
    fun testChooseCustomNumberOfPieces() = chooseCustomPieces(round, 20)

}
