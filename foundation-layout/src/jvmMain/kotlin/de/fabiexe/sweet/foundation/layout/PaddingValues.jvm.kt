package de.fabiexe.sweet.foundation.layout

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues as AndroidxPaddingValues

fun PaddingValues.toAndroidxPaddingValues() = AndroidxPaddingValues(
    start = left.dp,
    top = top.dp,
    end = right.dp,
    bottom = bottom.dp
)