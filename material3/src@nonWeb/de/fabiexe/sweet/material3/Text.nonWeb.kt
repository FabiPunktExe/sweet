package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.text.TextStyle
import de.fabiexe.sweet.ui.text.toAndroidxTextStyle
import androidx.compose.material3.LocalTextStyle as AndroidxLocalTextStyle
import androidx.compose.material3.Text as AndroidxText

@Composable
actual fun Text(
    text: String,
    modifier: Modifier,
    style: TextStyle?
) {
    AndroidxText(
        text = text,
        modifier = modifier.toAndroidxModifier(),
        style = AndroidxLocalTextStyle.current.merge(style?.toAndroidxTextStyle())
    )
}