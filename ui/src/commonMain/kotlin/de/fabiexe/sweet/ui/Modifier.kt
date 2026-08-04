package de.fabiexe.sweet.ui

interface Modifier {
    infix fun then(modifier: Modifier) = CombinedModifier(this, modifier)

    fun <T> fold(initial: T, operation: (T, Element) -> T) : T

    companion object : Modifier {
        override fun <T> fold(initial: T, operation: (T, Element) -> T): T = initial
    }

    interface Element : Modifier {
        override fun <T> fold(initial: T, operation: (T, Element) -> T) : T {
            return operation(initial, this)
        }
    }
}