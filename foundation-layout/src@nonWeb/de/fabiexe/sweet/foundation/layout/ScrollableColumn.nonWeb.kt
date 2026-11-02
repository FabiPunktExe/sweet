package de.fabiexe.sweet.foundation.layout

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.toHorizontalAndroidxAlignment
import androidx.compose.foundation.layout.Column as AndroidxColumn

@Composable
actual fun ScrollableColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement,
    horizontalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()
    AndroidxColumn(
        modifier = modifier.toAndroidxModifier().verticalScroll(scrollState),
        verticalArrangement = verticalArrangement.toVerticalAndroidxArrangement(),
        horizontalAlignment = horizontalAlignment.toHorizontalAndroidxAlignment()
    ) {
        content()
    }
}