package de.fabiexe.sweet.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import de.fabiexe.sweet.foundation.layout.PaddingElement
import androidx.compose.ui.Modifier as AndroidxModifier
import androidx.compose.ui.CombinedModifier as AndroidxCombinedModifier

fun Modifier.toAndroidxModifier(): AndroidxModifier = when (this) {
    is CombinedModifier -> AndroidxCombinedModifier(first.toAndroidxModifier(), second.toAndroidxModifier())
    is PaddingElement -> when {
        paddingValues.isAll() -> AndroidxModifier.padding(all = paddingValues.left.dp)
        paddingValues.isHorizontalVertical() -> AndroidxModifier.padding(
            horizontal = paddingValues.left.dp,
            vertical = paddingValues.top.dp
        )
        else -> AndroidxModifier.padding(
            start = paddingValues.left.dp,
            end = paddingValues.right.dp,
            top = paddingValues.top.dp,
            bottom = paddingValues.bottom.dp
        )
    }
    else -> AndroidxModifier.Companion
}