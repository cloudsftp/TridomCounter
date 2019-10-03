package de.melon.tridomcounter.data

import de.melon.tridomcounter.logic.Session

object GameData {

    val sessions = MutableList<Session?>(0) {null}

    fun addSession(session: Session) = sessions.add(session)

}