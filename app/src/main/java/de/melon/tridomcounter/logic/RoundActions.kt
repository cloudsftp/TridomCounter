package de.melon.tridomcounter.logic

class RoundActions {
    private fun complexMoveExample(i: Int) {}
    private fun simpleMoveExample() {}

    val complexActions = MutableList(0) {Pair<(Int) -> Unit, String>(::complexMoveExample, "")}
    val simpleActions = MutableList(0) {Pair<() -> Unit, String>(::simpleMoveExample, "")}

    fun clear() {
        complexActions.clear()
        simpleActions.clear()

    }

    fun addComplexMove(function: (Int) -> Unit, name: String) = complexActions.add(Pair(function, name))
    fun addSimpleMove(function: () -> Unit, name: String) = simpleActions.add(Pair(function, name))

    fun sizeOfComplexActions() = complexActions.size
    fun size() = complexActions.size + simpleActions.size

}
