package de.melon.tridomcounter.logic.round

import org.junit.Assert.assertEquals

fun checkPoints(round: Round, playerId: Int, points: Int)
        = assertEquals(points, round.getPoints(playerId))

fun chooseTridomVariant(round: Round, variant: Int) {
    assertEquals(3, round.cards.size)

    val roundCard = round.cards[variant]
    if (roundCard is ActionCardSimple)
        roundCard.function()

    else
        throw UnexpectedCardError(roundCard)

    assertEquals(round.session.players.size, round.cards.size)

}

fun chooseCustomTridomVariant(round: Round, pieces: Int) {
    assertEquals(3, round.cards.size)

    val roundCard = round.cards[2]
    if (roundCard is ActionCardComplex)
        roundCard.function(pieces)

    else
        throw UnexpectedCardError(roundCard)

    assertEquals(round.session.players.size, round.cards.size)

}

fun chooseFirstPlayer(round: Round, playerId: Int, players: Array<String>) {
    assertEquals(players.size, round.cards.size)

    val playerCard = round.cards[playerId]
    if (playerCard is ActionCardChoice)
        playerCard.function(playerId)

    else
        throw UnexpectedCardError(playerCard)

}

class UnexpectedCardError(card: Card)
    : AssertionError("Unexpected Card Type ${card}")
