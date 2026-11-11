package de.fabiexe.sweet.ui.text

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import de.fabiexe.sweet.ui.text.font.toAndroidxFontWeight
import androidx.compose.ui.text.TextStyle as AndroidxTextStyle

fun TextStyle.toAndroidxTextStyle() = AndroidxTextStyle(
    fontWeight = fontWeight?.toAndroidxFontWeight(),
    fontSize = fontSize?.sp ?: TextUnit.Unspecified,
    lineHeight = lineHeight?.sp ?: TextUnit.Unspecified,
    letterSpacing = letterSpacing?.sp ?: TextUnit.Unspecified
)