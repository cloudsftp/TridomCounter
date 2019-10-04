package de.melon.tridomcounter

import java.lang.Exception

fun assertException(e: Exception, expectedMessage: String) {
    assert(e.message.equals(expectedMessage)) { "was ${e.message}, expected ${expectedMessage}" }

}

