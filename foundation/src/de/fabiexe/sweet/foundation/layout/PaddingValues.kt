package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Immutable

@Immutable
data class PaddingValues(
    val left: Float,
    val right: Float,
    val top: Float,
    val bottom: Float
) {
    constructor(all: Float) : this(all, all, all, all)

    constructor(horizontal: Float, vertical: Float) : this(
        horizontal,
        horizontal,
        vertical,
        vertical
    )

    operator fun plus(other: PaddingValues) = PaddingValues(
        left + other.left,
        right + other.right,
        top + other.top,
        bottom + other.bottom
    )

    fun isAll() = left == right && right == top && top == bottom

    fun isHorizontalVertical() = left == right && top == bottom
}