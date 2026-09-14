package de.fabiexe.sweet.ui

import androidx.compose.ui.Alignment as AndroidxAlignment

fun Alignment.toHorizontalAndroidxAlignment(): AndroidxAlignment.Horizontal {
    return when (this) {
        Alignment.Start -> AndroidxAlignment.Start
        Alignment.Center -> AndroidxAlignment.CenterHorizontally
        Alignment.End -> AndroidxAlignment.End
    }
}

fun Alignment.toVerticalAndroidxAlignment(): AndroidxAlignment.Vertical {
    return when (this) {
        Alignment.Start -> AndroidxAlignment.Top
        Alignment.Center -> AndroidxAlignment.CenterVertically
        Alignment.End -> AndroidxAlignment.Bottom
    }
}