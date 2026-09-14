package de.fabiexe.sweet.ui.input.pointer

import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.Modifier

@Immutable
data class PointerHoverIconElement(val pointerIcon: PointerIcon) : Modifier.Element

fun Modifier.pointerHoverIcon(icon: PointerIcon) = this then PointerHoverIconElement(icon)