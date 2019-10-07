package de.melon.tridomcounter.logic

class Session(val players: Array<String>) {

    val numberOfPlayers = players.size

    internal val rounds = MutableList(0) {Round(this, 0)}

    fun newRound(firstPlayerId: Int) : Int {
        val round = Round(this, firstPlayerId)
        rounds.add(round)

        return rounds.indexOf(round)

    }

}