package de.melon.tridomcounter.logic

import de.melon.tridomcounter.logic.exceptions.TooManyPlayersException
import org.junit.Before

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
        session = Session(numberOfPlayers)

        assert(session.players.size == numberOfPlayers)

    }

    @Test
    fun t02_setPlayers() {
        session.addPlayer(player1)
        session.addPlayer(player2)

        assert(session.players[0].equals(player1))
        assert(session.players[1].equals(player2))

        try {
            session.addPlayer(player3)

        } catch (e: TooManyPlayersException) {
            assertException(e, "Too many players: #3 Tim")

        }

    }

    @Test
    fun t03_addRound() {
        val round = session.newRound()

        assert(round.session == session)

    }

}