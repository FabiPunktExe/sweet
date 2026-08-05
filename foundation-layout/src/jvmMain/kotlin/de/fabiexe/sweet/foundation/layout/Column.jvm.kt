package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.toHorizontalAndroidxAlignment
import androidx.compose.foundation.layout.Column as FoundationColumn

@Composable
actual fun Column(
    modifier: Modifier,
    verticalArrangement: Arrangement,
    horizontalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    FoundationColumn(
        modifier = modifier.toAndroidxModifier(),
        verticalArrangement = verticalArrangement.toVerticalAndroidxArrangement(),
        horizontalAlignment = horizontalAlignment.toHorizontalAndroidxAlignment()
    ) {
        content()
    }
}