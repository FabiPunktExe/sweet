package de.fabiexe.sweet.foundation.layout

import de.fabiexe.sweet.ui.Modifier

data class PaddingElement(val paddingValues: PaddingValues) : Modifier.Element

fun Modifier.padding(paddingValues: PaddingValues): Modifier {
    return then(PaddingElement(paddingValues))
}

fun Modifier.padding(horizontal: Float, vertical: Float): Modifier {
    return then(PaddingElement(PaddingValues(horizontal, vertical)))
}

fun Modifier.padding(left: Float, right: Float, top: Float, bottom: Float): Modifier {
    return then(PaddingElement(PaddingValues(left, right, top, bottom)))
}