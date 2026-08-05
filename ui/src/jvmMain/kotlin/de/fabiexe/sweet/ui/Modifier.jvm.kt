package de.fabiexe.sweet.ui

import androidx.compose.ui.input.pointer.pointerHoverIcon
import de.fabiexe.sweet.ui.input.pointer.PointerHoverIconElement
import de.fabiexe.sweet.ui.input.pointer.toAndroidxPointerIcon
import androidx.compose.ui.CombinedModifier as AndroidxCombinedModifier
import androidx.compose.ui.Modifier as AndroidxModifier

fun Modifier.toAndroidxModifier(): AndroidxModifier = when (this) {
    is CombinedModifier -> AndroidxCombinedModifier(first.toAndroidxModifier(), second.toAndroidxModifier())
    is PointerHoverIconElement -> AndroidxModifier.pointerHoverIcon(pointerIcon.toAndroidxPointerIcon())
    else -> AndroidxModifier.Companion
}