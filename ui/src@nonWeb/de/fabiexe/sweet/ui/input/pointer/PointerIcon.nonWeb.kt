package de.fabiexe.sweet.ui.input.pointer

import androidx.compose.ui.input.pointer.PointerIcon as AndroidxPointerIcon

fun PointerIcon.toAndroidxPointerIcon() = when (this) {
    PointerIcon.Default -> AndroidxPointerIcon.Default
    PointerIcon.Crosshair -> AndroidxPointerIcon.Crosshair
    PointerIcon.Text -> AndroidxPointerIcon.Text
    PointerIcon.Hand -> AndroidxPointerIcon.Hand
}