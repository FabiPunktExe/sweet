package de.fabiexe.sweet.foundation.layout

import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.fabiexe.sweet.ui.CombinedModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.toAndroidxModifier
import androidx.compose.ui.CombinedModifier as AndroidxCombinedModifier
import androidx.compose.ui.Modifier as AndroidxModifier

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
    is SizeElement -> when {
        minWidth != null && maxWidth != null && minHeight == null && maxHeight == null ->
            if (minWidth == maxWidth) {
                AndroidxModifier.width(minWidth.dp)
            } else {
                AndroidxModifier.widthIn(minWidth.dp, maxWidth.dp)
            }
        minWidth == null && maxWidth == null && minHeight != null && maxHeight != null ->
            if (minHeight == maxHeight) {
                AndroidxModifier.height(minHeight.dp)
            } else {
                AndroidxModifier.heightIn(minHeight.dp, maxHeight.dp)
            }
        minWidth != null && minWidth == minHeight && maxWidth != null && maxWidth == maxHeight ->
            AndroidxModifier.size(minWidth.dp)
        else -> AndroidxModifier.sizeIn(
            minWidth = minWidth?.dp ?: Dp.Unspecified,
            maxWidth = maxWidth?.dp ?: Dp.Unspecified,
            minHeight = minHeight?.dp ?: Dp.Unspecified,
            maxHeight = maxHeight?.dp ?: Dp.Unspecified
        )
    }
    is FillElement -> when (direction) {
        FillElement.Direction.Horizontal -> AndroidxModifier.fillMaxWidth(fraction)
        FillElement.Direction.Vertical -> AndroidxModifier.fillMaxHeight(fraction)
        FillElement.Direction.Both -> AndroidxModifier.fillMaxSize(fraction)
    }
    else -> toAndroidxModifier()
}