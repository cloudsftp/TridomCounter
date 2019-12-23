package de.melon.tridomcounter.logic

import org.junit.Before
import org.junit.Test

class SessionTests {

    val numberOfPlayers = 2

    val player1 = "Fabian"
    val player2 = "Paul"
    val player3 = "Tim"

    companion object {
        lateinit var session: Session

    }

    @Before
    @Test
    fun setUp() {
        val players = Array(numberOfPlayers) {String()}
        session = Session(players)

        assert(session.numberOfPlayers == numberOfPlayers)

    }

    @Test
    fun addRound() {
        val roundId = session.newRound()
        val round = session.rounds[roundId]

        assert(round.session == session)

    }

}