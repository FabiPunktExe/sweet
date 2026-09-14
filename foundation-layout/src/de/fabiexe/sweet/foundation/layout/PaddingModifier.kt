package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.Modifier

@Immutable
data class PaddingElement(val paddingValues: PaddingValues) : Modifier.Element

fun Modifier.padding(paddingValues: PaddingValues) = this then PaddingElement(paddingValues)
fun Modifier.padding(all: Float) = this then PaddingElement(PaddingValues(all))
fun Modifier.padding(horizontal: Float = 0f, vertical: Float = 0f) = this then PaddingElement(PaddingValues(horizontal, vertical))
fun Modifier.padding(
    left: Float = 0f,
    right: Float = 0f,
    top: Float = 0f,
    bottom: Float = 0f
) = this then PaddingElement(PaddingValues(left, right, top, bottom))