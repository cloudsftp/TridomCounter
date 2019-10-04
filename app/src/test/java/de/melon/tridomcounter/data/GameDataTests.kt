package de.melon.tridomcounter.data

import de.melon.tridomcounter.logic.Session
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GameDataTests {

    val session = Session(Array(1) { String()})

    @Test
    fun t01_addSession() {
        val sessionId = GameData.addSession(session)

        assert(sessionId == 0)

    }

    @Test
    fun t02_clearSessions() {
        GameData.clear()

        assert(GameData.sessions.size == 0)

    }

}
