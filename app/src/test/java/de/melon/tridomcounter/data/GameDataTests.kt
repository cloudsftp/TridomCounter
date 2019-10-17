package de.melon.tridomcounter.data

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GameDataTests {

    @Test
    fun t01_addSession() {
        val sessionId = GameData.newSession(arrayOf(String()))

        assert(sessionId == 0)

    }

    @Test
    fun t02_clearSessions() {
        GameData.clear()

        assert(GameData.sessions.size == 0)

    }

}
