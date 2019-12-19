package de.melon.tridomcounter.logic.round

abstract class Card {
    abstract val displayText: String

}

class DisplayCard(override val displayText: String) : Card()

class ActionCardSimple(override val displayText: String, val function: () -> Unit) : Card()

class ActionCardChoice(override val displayText: String, val function: (Int) -> Unit) : Card()

class ActionCardComplex(override val displayText: String, val function: (Int) -> Unit) : Card()
