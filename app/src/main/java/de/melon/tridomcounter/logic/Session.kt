package de.melon.tridomcounter.logic

class Session(val players: Array<String>) {

    val numberOfPlayers = players.size

    internal val rounds = MutableList(0) {Round(this)}

    fun newRound(): Round {
        val round = Round(this)
        rounds.add(round)

        return round

    }

}