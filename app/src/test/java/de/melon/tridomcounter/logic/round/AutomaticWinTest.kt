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
class AutomaticWinTest {

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
    fun noDraws() {
        var currentPlayer = 1

        for (i in 0 until 2) {
            currentPlayer = 1
            choosePlace(round, 0)

            currentPlayer = 2
            choosePlace(round, 0)

            currentPlayer = 0
            choosePlace(round, 0)

        }

        checkWin(round)

        checkPointsAndUpdate(currentPlayer, 25)

    }

    @Test
    fun addBonus() {
        noDraws()

        val currentPlayer = 0

        chooseAddBonus(round, 15)
        checkPointsAndUpdate(currentPlayer, 15)

    }

    @Test
    fun finish() {
        noDraws()

        chooseFinish(round)

        checkDone(round)

    }

    @Test
    fun undoWinningMove() {
        noDraws()

        val currentPlayer = 0

        chooseUndo(round)
        checkPointsAndUpdate(currentPlayer, -25)

        choosePlace(round, 0)
        checkPointsAndUpdate(currentPlayer, 25)

        checkWin(round)

    }

    @Test
    fun undoAddBonus() {
        addBonus()

        val currentPlayer = 0

        chooseUndo(round)
        checkPointsAndUpdate(currentPlayer, -15)

    }

    @Test
    fun undoFinish() {
        finish()

        val currentPlayer = 0

        chooseUndo(round)

        checkWin(round)

    }

    private fun checkPointsAndUpdate(playerId: Int, delta: Int) {
        points[playerId] += delta
        checkPoints(round, playerId, points[playerId])

    }

}
