package de.melon.tridomcounter.logic

class Session(val players: Array<String>) : PointInterface {

    val numberOfPlayers = players.size

    internal val rounds = MutableList(0) {Round(this)}

    override fun getPoints(id: Int) = rounds.sumBy { r -> r.getPoints(id) }

    fun newRound() : Int {
        val round = Round(this)
        rounds.add(round)

        return rounds.indexOf(round)

    }

}