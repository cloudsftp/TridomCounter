package de.melon.tridomcounter.logic.round

import android.content.Context
import de.melon.tridomcounter.logic.Session
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChooseVariantTest {

    @Mock
    lateinit var context: Context

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    @Test
    @Before
    fun instantiate() {
        val session = Session(players)
        round = Round(session)

        doReturn("").`when`(context).getString(anyInt())
        round.context = context

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
