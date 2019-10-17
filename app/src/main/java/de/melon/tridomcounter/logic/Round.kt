package de.melon.tridomcounter.logic

class Round(val session: Session, var currentPlayerId: Int) {
    val moves = Array(session.numberOfPlayers) {MutableList<Move>(0) {BaseMove}}

    fun getPoints(playerId: Int) = moves[playerId].sumBy { m -> m.points }

    fun makeMove(move: Move) {
        moves[currentPlayerId].add(move)
        finishMove()

    }

    fun finishMove() {
        currentPlayerId++

        if (currentPlayerId >= session.numberOfPlayers)
            currentPlayerId = 0

    }

}