package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.Box
import de.fabiexe.sweet.foundation.layout.height
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
fun InteractiveComponent(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.height(minHeight = 48f, maxHeight = null),
        horizontalAlignment = Alignment.Center,
        verticalAlignment = Alignment.Center,
        content = content
    )
}