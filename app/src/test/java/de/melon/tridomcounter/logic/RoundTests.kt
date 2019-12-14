package de.melon.tridomcounter.logic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class RoundTests {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 0

    @Test
    @Before
    fun instantiate() {
        val session = Session(players)
        round = Round(session, firstPlayer)

        checkPoints(firstPlayer, 20)

    }

    @Test
    fun makeSimpleDrawMove() {
        draw()

        checkPoints(firstPlayer, 15)

    }

    @Test
    fun makeSimpleMove() {
        place(10)

        checkPoints(firstPlayer, 30)

    }

    @Test
    fun drawAndPlace() {
        draw()
        place(10)

        checkPoints(firstPlayer, 25)

    }

    @Test
    fun drawThreeTimes() {
        for (i in 0 until 3)
            draw()

        checkPoints(firstPlayer, 5)

    }

    @Test
    fun drawPlaceAndPass() {
        draw()
        place(20)

        pass()

        checkPoints(firstPlayer, 35)

    }

    @Test
    fun drawThreeTimesAndPass() {
        for (i in 0 until 3)
            draw()

        pass()

        checkPoints(firstPlayer, -5)

    }

    @Test
    fun disablingDrawButton() {
        for (i in 0 until 3)
            draw()

        checkDrawNotPossible()

    }

    @Test
    fun disablingPlaceButton() {
        place(10)

        checkPlaceNotPossible()

    }

    @Test
    fun displayingPlace() {
        place(10)

        checkMoves(10, 20)

    }

    @Test
    fun displayDraw() {
        draw()

        checkMoves(-5, 20)

    }

    @Test
    fun displayDrawAndPlace() {
        draw()
        draw()
        place(10)

        checkMoves(10, -5, -5, 20)

    }

    private fun checkPoints(player: Int, points: Int) = assert(round.getPoints(player) == points) { "expected $points, actual ${round.getPoints(player)}" }

    private fun pass() {
        val passCard = round.cards[0] as ActionCardSimple
        passCard.function()
    }

    private val placeCardIndex = 1

    private fun checkPlaceNotPossible() {
        if (placeCardIndex < round.cards.size)
            assertFalse(round.cards[placeCardIndex] is ActionCardComplex)

    }

    private fun place(points: Int) {
        val placeCard = round.cards[placeCardIndex] as ActionCardComplex
        placeCard.function(points)

    }

    private val drawCardIndex = 2

    private fun checkDrawNotPossible() {
        if (drawCardIndex < round.cards.size)
            assertFalse(round.cards[drawCardIndex] is ActionCardSimple)

    }

    private fun draw() {
        val drawCard = round.cards[drawCardIndex] as ActionCardSimple
        drawCard.function()

    }

    private fun checkMoves(vararg points: Int) {
        var i = 0

        for (card in round.cards)
            if (card is DisplayCard) {
                assertEquals(card.displayText, points[i].toString())
                i++

            }

        if (i == 0 && points.isNotEmpty())
            throw AssertionError("No moves displayed!")

    }

}
