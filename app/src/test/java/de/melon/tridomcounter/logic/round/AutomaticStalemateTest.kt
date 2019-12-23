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
class AutomaticStalemateTest {

    @Mock
    lateinit var context: Context

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 0

    lateinit var points: Array<Int>

    @Test
    @Before
    fun setUp() {
        val session = Session(players)
        round = Round(session)

        Mockito.doReturn("").`when`(context).getString(Matchers.anyInt())
        round.context = context

        chooseCustomTridomVariant(round, 6)

        chooseCustomPieces(round, 3)

        chooseFirstPlayer(round, firstPlayer, players)

        chooseFirstPiece(round, firstPlayer, 0, 60)
        points = arrayOf(60, 0, 0)

    }

    @Test
    fun drawOnly() {
        var currentPlayer = 1
        for (i in 0 until 3)
            chooseDraw(round)
        choosePass(round)

        currentPlayer = 2
        for (i in 0 until 3)
            chooseDraw(round)
        choosePass(round)

        currentPlayer = 0
        choosePass(round)

        checkStalemate(round)

    }

    @Test
    fun punish() {
        drawOnly()

        for (i in 0 until 3) {
            chooseAddPunishment(round, 10)
            chooseContinuePunishment(round)

        }

        checkPoints(round, 0, 40)
        checkPoints(round, 1, -35)
        checkPoints(round, 2, -35)

    }

    @Test
    fun done() {
        punish()

        checkDone(round)

    }

}