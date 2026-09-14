package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Immutable
import de.fabiexe.sweet.ui.Modifier

@Immutable
data class SizeElement(
    val minWidth: Float? = null,
    val maxWidth: Float? = null,
    val minHeight: Float? = null,
    val maxHeight: Float? = null
) : Modifier.Element

fun Modifier.width(width: Float) = this then SizeElement(minWidth = width, maxWidth = width)
fun Modifier.width(minWidth: Float?, maxWidth: Float?) = this then SizeElement(minWidth = minWidth, maxWidth = maxWidth)
fun Modifier.height(height: Float) = this then SizeElement(minHeight = height, maxHeight = height)
fun Modifier.height(minHeight: Float?, maxHeight: Float?) = this then SizeElement(minHeight = minHeight, maxHeight = maxHeight)
fun Modifier.size(size: Float) = this then SizeElement(size, size, size, size)

@Immutable
data class FillElement(val direction: Direction, val fraction: Float) : Modifier.Element {
    enum class Direction {
        Horizontal, Vertical, Both
    }
}

fun Modifier.fillMaxWidth(fraction: Float = 1f) = this then FillElement(FillElement.Direction.Horizontal, fraction)
fun Modifier.fillMaxHeight(fraction: Float = 1f) = this then FillElement(FillElement.Direction.Vertical, fraction)
fun Modifier.fillMaxSize(fraction: Float = 1f) = this then FillElement(FillElement.Direction.Both, fraction)