package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import androidx.compose.material3.Text as AndroidxText

@Composable
actual fun Text(
    text: String,
    modifier: Modifier
) {
    AndroidxText(
        text = text,
        modifier = modifier.toAndroidxModifier()
    )
}