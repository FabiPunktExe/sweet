package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.*
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