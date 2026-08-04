package de.fabiexe.sweet.ui

data class CombinedModifier(val first: Modifier, val second: Modifier) : Modifier {
    override fun <T> fold(initial: T, operation: (T, Modifier.Element) -> T): T {
        return second.fold(first.fold(initial, operation), operation)
    }
}