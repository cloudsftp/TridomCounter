package de.melon.tridomcounter.logic.round

import org.junit.Assert.assertEquals

fun checkPoints(round: Round, playerId: Int, points: Int)
        = assertEquals(points, round.getPoints(playerId))

fun chooseTridomVariant(round: Round, variant: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeSimpleActionCard(round.cards[variant])

}

fun chooseCustomTridomVariant(round: Round, pieces: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeComplexActionCard(round.cards[2], pieces)

}

fun choose7Pieces(round: Round) = choosePieces(round, 0)
fun choose9Pieces(round: Round) = choosePieces(round, 1)
fun choosePieces(round: Round, option: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeSimpleActionCard(round.cards[option])

}

fun chooseCustomPieces(round: Round, pieces: Int) {
    update(round)

    assertEquals(3, round.cards.size)

    invokeComplexActionCard(round.cards[2], pieces)

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

fun checkWin(round: Round) {
    update(round)

    assertEquals(4, round.cards.size)

    assert(round.cards[0] is ActionCardSimple)
    assert(round.cards[1] is ActionCardComplex)
    assert(round.cards[2] is DisplayCard)
    assert(round.cards[3] is DisplayCard)

}

fun chooseFinish(round: Round) {
    update(round)

    invokeSimpleActionCard(round.cards[0])

}

fun chooseAddBonus(round: Round, points: Int) {
    update(round)

    invokeComplexActionCard(round.cards[1], points)

}

fun checkStalemate(round: Round) {
    update(round)

    assert(round.cards[0] is ActionCardSimple)
    assert(round.cards[1] is ActionCardComplex)

}

fun chooseAddPunishment(round: Round, points: Int) {
    checkStalemate(round)

    invokeComplexActionCard(round.cards[1], points)

}

fun chooseContinuePunishment(round: Round) {
    checkStalemate(round)

    invokeSimpleActionCard(round.cards[0])

}

fun checkDone(round: Round) {
    update(round)

    assertEquals(1, round.cards.size)

}

fun chooseUndo(round: Round) {
    update(round)

    invokeSimpleActionCard(round.cards.last())

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
