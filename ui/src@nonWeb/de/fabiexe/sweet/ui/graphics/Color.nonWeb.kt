package de.fabiexe.sweet.ui.graphics

import androidx.compose.ui.graphics.Color as AndroidxColor

fun Color.toAndroidxColor() = AndroidxColor(value)
fun AndroidxColor.toSweetColor() = Color((value shr 32).toInt())