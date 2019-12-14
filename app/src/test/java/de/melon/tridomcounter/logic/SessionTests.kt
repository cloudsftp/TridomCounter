package de.melon.tridomcounter.logic

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SessionTests {

    val numberOfPlayers = 2

    val player1 = "Fabian"
    val player2 = "Paul"
    val player3 = "Tim"

    companion object {
        lateinit var session: Session

    }

    @Test
    fun t01_instantiation() {
        val players = Array(numberOfPlayers) {String()}
        session = Session(players)

        assert(session.numberOfPlayers == numberOfPlayers)

    }

    @Test
    fun t02_addRound() {
        val roundId = session.newRound(0)
        val round = session.rounds[roundId]

        assert(round.session == session)

    }

}