package de.melon.tridomcounter.logic

import de.melon.tridomcounter.logic.exceptions.TooManyPlayersException

class Session(numOfPlayers: Int) {

    internal val rounds = MutableList(0) {Round(this)}

    val players = Array(numOfPlayers) { String() }
    internal var playersSet = 0

    fun addPlayer(player: String) {
        if (playersSet < players.size) {
            players[playersSet] = player
            playersSet++

        } else {
            throw TooManyPlayersException(players.size + 1, player)

        }

    }

    fun newRound(): Round {
        val round = Round(this)
        rounds.add(round)

        return round

    }

}