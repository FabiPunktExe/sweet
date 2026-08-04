package de.fabiexe.sweet.ui

import de.fabiexe.sweet.ui.Modifier.Element

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

inline fun <T, reified E : Element> Modifier.fold(initial: T, crossinline operation: (T, E) -> T) : T {
    return fold(initial) { acc, element ->
        if (element is E) {
            operation(acc, element)
        } else {
            initial
        }
    }
}