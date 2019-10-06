package de.melon.tridomcounter.data

import de.melon.tridomcounter.logic.Session

object GameData {

    val sessions = MutableList(0) { Session(Array(0){String()}) }

    fun addSession(session: Session) = sessions.add(session).let { sessions.indexOf(session) }

    fun clear() = sessions.clear()

}