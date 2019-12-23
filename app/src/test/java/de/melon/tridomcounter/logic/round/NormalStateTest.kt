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
class NormalStateTest {

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

        chooseCustomTridomVariant(round, 20)

        choose7Pieces(round)

        chooseFirstPlayer(round, firstPlayer, players)

        chooseFirstPiece(round, firstPlayer, 0, 60)
        points = arrayOf(60, 0, 0)

    }

    @Test
    fun place() {
        var currentPlayer = 1
        choosePlace(round, 14)
        checkPointsAndUpdate(currentPlayer, 14)

        currentPlayer = 2
        choosePlace(round, 2)
        checkPointsAndUpdate(currentPlayer, 2)

        currentPlayer = 0
        choosePlace(round, 5)
        checkPointsAndUpdate(currentPlayer, 5)

        currentPlayer = 1
        choosePlace(round, 3)
        checkPointsAndUpdate(currentPlayer, 3)

    }

    @Test
    fun draw() {
        val currentPlayer = 1
        chooseDraw(round)
        checkPointsAndUpdate(currentPlayer, -5)

    }

    @Test
    fun drawAndPlace() {
        var currentPlayer = 1
        chooseDraw(round)
        choosePlace(round, 10)
        checkPointsAndUpdate(currentPlayer, 5)
        points[currentPlayer] += 5

        currentPlayer = 2
        for (i in 0 until 2)
            chooseDraw(round)
        choosePlace(round, 5)
        checkPointsAndUpdate(currentPlayer, -5)

        currentPlayer = 0
        for (i in 0 until 3)
            chooseDraw(round)
        choosePlace(round, 12)
        checkPointsAndUpdate(currentPlayer, -3)

    }

    @Test
    fun drawThreeTimesAndPass() {
        val currentPlayer = 1
        for (i in 0 until 3)
            chooseDraw(round)
        choosePass(round)
        checkPointsAndUpdate(currentPlayer, -25)

    }

    fun checkPointsAndUpdate(playerId: Int, delta: Int) {
        points[playerId] += delta
        checkPoints(round, playerId, points[playerId])

    }

}
