package de.melon.tridomcounter.logic.exceptions

import java.lang.Exception

class TooManyPlayersException : Exception {

    constructor(num: Int, name: String) : super("Too many players: #${num+1} $name")

}