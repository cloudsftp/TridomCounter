package de.melon.tridomcounter.logic

import de.melon.tridomcounter.logic.exceptions.TooManyPlayersException

class Session(val numOfPlayers: Int) {

    internal val rounds = MutableList(0) {Round()}

    val players = Array(numOfPlayers) { String() }
    internal var playersSet = 0

    fun addPlayer(player: String) {
        if (playersSet < players.size) {
            players[playersSet] = player
            playersSet++

        } else {
            throw TooManyPlayersException(playersSet, player)

        }

    }

}