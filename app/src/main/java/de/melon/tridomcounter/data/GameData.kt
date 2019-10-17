package de.melon.tridomcounter.data

import de.melon.tridomcounter.logic.Session

object GameData {

    val sessions = MutableList(0) { Session(Array(0){String()}) }

    fun newSession(players: Array<String>) : Int {
        val session = Session(players)
        sessions.add(session)

        return sessions.indexOf(session)

    }

    fun clear() = sessions.clear()

}