package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.Modifier

@Immutable
data class PaddingElement(val paddingValues: PaddingValues) : Modifier.Element

fun Modifier.padding(paddingValues: PaddingValues) = this then PaddingElement(paddingValues)
fun Modifier.padding(horizontal: Float, vertical: Float) = this then PaddingElement(PaddingValues(horizontal, vertical))
fun Modifier.padding(left: Float, right: Float, top: Float, bottom: Float) = this then PaddingElement(PaddingValues(left, right, top, bottom))