package de.melon.tridomcounter.logic

class Session(val players: Array<String>) : PointInterface {

    val numberOfPlayers = players.size

    internal val rounds = MutableList(0) {Round(this, 0)}

    override fun getPoints(id: Int) = rounds.sumBy { r -> r.getPoints(id) }

    fun newRound(firstPlayerId: Int) : Int {
        val round = Round(this, firstPlayerId)
        rounds.add(round)

        return rounds.indexOf(round)

    }

}