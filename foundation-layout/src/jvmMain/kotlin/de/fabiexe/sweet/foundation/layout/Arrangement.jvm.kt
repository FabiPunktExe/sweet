package de.fabiexe.sweet.foundation.layout

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement as AndroidxArrangement
import androidx.compose.ui.Alignment as AndroidxAlignment

fun Arrangement.toHorizontalAndroidxArrangement(): AndroidxArrangement.Horizontal {
    return if (space != null) {
        val alignment = when (orientation) {
            Arrangement.Orientation.Start -> AndroidxAlignment.Start
            Arrangement.Orientation.Center -> AndroidxAlignment.CenterHorizontally
            Arrangement.Orientation.End -> AndroidxAlignment.End
        }
        AndroidxArrangement.spacedBy(space.dp, alignment)
    } else {
        when (orientation) {
            Arrangement.Orientation.Start -> AndroidxArrangement.Start
            Arrangement.Orientation.Center -> AndroidxArrangement.Center
            Arrangement.Orientation.End -> AndroidxArrangement.End
        }
    }
}

fun Arrangement.toVerticalAndroidxArrangement(): AndroidxArrangement.Vertical {
    return if (space != null) {
        val alignment = when (orientation) {
            Arrangement.Orientation.Start -> AndroidxAlignment.Top
            Arrangement.Orientation.Center -> AndroidxAlignment.CenterVertically
            Arrangement.Orientation.End -> AndroidxAlignment.Bottom
        }
        AndroidxArrangement.spacedBy(space.dp, alignment)
    } else {
        when (orientation) {
            Arrangement.Orientation.Start -> AndroidxArrangement.Top
            Arrangement.Orientation.Center -> AndroidxArrangement.Center
            Arrangement.Orientation.End -> AndroidxArrangement.Bottom
        }
    }
}