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

        chooseCustomTridomVariant(round, 12)

        chooseCustomPieces(round, 2)

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
    fun undoStalemate() {
        drawOnly()

        val currentPlayer = 0

        chooseUndo(round)
        choosePass(round)
        checkStalemate(round)

    }

    @Test
    fun undoPunish() {
        drawOnly()

        for (i in 0 until 2) {
            chooseAddPunishment(round, 10)
            chooseAddPunishment(round, 10)
            chooseContinuePunishment(round)

        }

        chooseAddPunishment(round, 10)
        chooseAddPunishment(round, 10)

        var currentPlayer = 0

        checkPoints(round, currentPlayer, 30)
        chooseUndo(round)
        checkPoints(round, currentPlayer, 40)
        chooseUndo(round)
        checkPoints(round, currentPlayer, 50)

        currentPlayer = 2
        checkPoints(round, currentPlayer, -45)
        chooseUndo(round)
        checkPoints(round, currentPlayer, -35)

    }

    @Test
    fun finish() {
        punish()

        checkDone(round)

    }

    @Test
    fun undoFinish() {
        finish()

        chooseUndo(round)
        chooseContinuePunishment(round)
        checkDone(round)

    }

}