package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.toHorizontalAndroidxAlignment
import de.fabiexe.sweet.ui.toVerticalAndroidxAlignment
import androidx.compose.foundation.layout.Box as AndroidxBox

@Composable
actual fun Box(
    modifier: Modifier,
    horizontalAlignment: Alignment,
    verticalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    AndroidxBox(
        modifier = modifier.toAndroidxModifier(),
        contentAlignment = horizontalAlignment.toHorizontalAndroidxAlignment() + verticalAlignment.toVerticalAndroidxAlignment()
    ) {
        content()
    }
}