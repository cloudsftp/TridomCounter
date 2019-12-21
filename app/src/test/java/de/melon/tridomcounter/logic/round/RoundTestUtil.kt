package de.melon.tridomcounter.logic.round

import org.junit.Assert.assertEquals

fun checkPoints(round: Round, playerId: Int, points: Int)
        = assertEquals(points, round.getPoints(playerId))

fun chooseTridomVariant(round: Round, variant: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeSimpleActionCard(round.cards[variant])

    assertEquals(round.session.players.size, round.cards.size)

}

fun chooseCustomTridomVariant(round: Round, pieces: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeComplexActionCard(round.cards[2], pieces)

    assertEquals(round.session.players.size, round.cards.size)

}

fun chooseFirstPlayer(round: Round, playerId: Int, players: Array<String>) {
    update(round)

    assertEquals(players.size, round.cards.size)

    invokeChoiceActionCard(round.cards[playerId], playerId)

}

fun chooseFirstPiece(round: Round, playerId: Int, number: Int, expected: Int) {
    update(round)

    invokeChoiceActionCard(round.cards[number], number)

    assertEquals(expected, round.getPoints(playerId))

}

fun chooseCustomFirstPiece(round: Round, playerId: Int, points: Int) {
    update(round)

    invokeComplexActionCard(round.cards[6], points)

    assertEquals(20 + points, round.getPoints(playerId))

}

fun choosePlace(round: Round, points: Int) {
    update(round)

    invokeComplexActionCard(round.cards[0], points)

}

fun choosePass(round: Round) = chooseDraw(round)
fun chooseDraw(round: Round) {
    update(round)

    invokeSimpleActionCard(round.cards[1])

}

fun update(round: Round) = round.updateCards()

fun invokeComplexActionCard(card: Card, arg: Int)
        =   if (card is ActionCardComplex)
                card.function(arg)
            else throw UnexpectedCardError(card)

fun invokeChoiceActionCard(card: Card, arg: Int)
        =   if (card is ActionCardChoice)
                card.function(arg)
            else throw UnexpectedCardError(card)

fun invokeSimpleActionCard(card: Card)
        =   if (card is ActionCardSimple)
                card.function()
            else throw UnexpectedCardError(card)

class UnexpectedCardError(card: Card)
    : AssertionError("Unexpected Card Type $card")