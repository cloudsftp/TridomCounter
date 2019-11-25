package de.melon.tridomcounter.logic

import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

class RoundTests {

    val players = arrayOf("Fabian", "Paul", "Tim")
    lateinit var round: Round

    val firstPlayer = 0

    @Test
    @Before
    fun instantiate() {
        val session = Session(players)
        round = Round(session, firstPlayer)

    }

    @Test
    fun makeSimpleDrawMove() {
        draw()

        assertPoints(firstPlayer, 15)

    }

    @Test
    fun makeSimpleMove() {
        place(10)

        assertPoints(firstPlayer, 30)

    }

    @Test
    fun drawAndPlace() {
        draw()
        place(10)

        assertPoints(firstPlayer, 25)

    }

    @Test
    fun drawThreeTimes() {
        for (i in 0 until 3)
            draw()

        checkDrawNotPossible()
        assertPoints(firstPlayer, 5)

    }

    @Test
    fun drawPlaceAndPass() {
        draw()
        place(20)


    }

    @Test
    fun drawThreeTimesAndPass() {
        for (i in 0 until 3)
            draw()

        pass()

        assertPoints(firstPlayer, -5)


    }

    private fun assertPoints(player: Int, points: Int) = assert(round.getPoints(player) == points) { "expected $points, actual ${round.getPoints(player)}" }

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

}
