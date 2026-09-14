package de.fabiexe.sweet.ui.graphics

import androidx.compose.runtime.Immutable
import kotlin.math.round

@Immutable
data class Color(val value: Int) {
    constructor(value: Long) : this(value.toInt())

    constructor(
        red: Int,
        green: Int,
        blue: Int,
        alpha: Int
    ): this((red shl 16) or (green shl 8) or blue or (alpha shl 24))

    fun copy(
        red: Int = this.red,
        green: Int = this.green,
        blue: Int = this.blue,
        alpha: Int = this.alpha
    ) = Color(red, green, blue, alpha)

    fun copy(
        red: Float = this.red / 255f,
        green: Float = this.green / 255f,
        blue: Float = this.blue / 255f,
        alpha: Float = this.alpha / 255f
    ) = Color(
        round(red * 255).toInt(),
        round(green * 255).toInt(),
        round(blue * 255).toInt(),
        round(alpha * 255).toInt()
    )
}

val Color.red: Int get() = (value shr 16) and 0xFF
val Color.green: Int get() = (value shr 8) and 0xFF
val Color.blue: Int get() = value and 0xFF
val Color.alpha: Int get() = (value shr 24) and 0xFF