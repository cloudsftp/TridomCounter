package de.melon.tridomcounter.logic

abstract class Card {
    abstract val displayText: String

}

class DisplayCard(override val displayText: String) : Card()

class ActionCardSimple(override val displayText: String, val function: () -> Unit) : Card()

class ActionCardComplex(override val displayText: String, val function: (i: Int) -> Unit) : Card()
