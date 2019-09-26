package de.melon.tridomcounter.logic

import de.melon.tridomcounter.logic.exceptions.TooManyPlayersException
import org.junit.Before

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SessionTests {

    val numberOfPlayers = 2

    lateinit var session: Session

    val player1 = "Fabian"
    val player2 = "Paul"

    @Test
    @Before
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

    }

    val player3 = "Tim"

    @Test
    fun t03_setTooManyPlayers() {
        session.addPlayer(player1)
        session.addPlayer(player2)

        assert(session.players[0].equals(player1))
        assert(session.players[1].equals(player2))

        try {
            session.addPlayer(player3)

        } catch (e: TooManyPlayersException) {
            assert(e.message.equals("Too many players: #3 Tim"))

        }

    }

}