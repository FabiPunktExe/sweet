package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.toAndroidxModifier
import de.fabiexe.sweet.ui.toVerticalAndroidxAlignment
import androidx.compose.foundation.layout.Row as AndroidxRow

@Composable
actual fun Row(
    modifier: Modifier,
    horizontalArrangement: Arrangement,
    verticalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    AndroidxRow(
        modifier = modifier.toAndroidxModifier(),
        horizontalArrangement = horizontalArrangement.toHorizontalAndroidxArrangement(),
        verticalAlignment = verticalAlignment.toVerticalAndroidxAlignment()
    ) {
        content()
    }
}