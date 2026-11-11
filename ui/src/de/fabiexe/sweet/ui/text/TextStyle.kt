package de.fabiexe.sweet.ui.text

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import de.fabiexe.sweet.ui.text.font.FontWeight

@Immutable
data class TextStyle(
    val fontWeight: FontWeight? = null,
    val fontSize: Float? = null,
    val lineHeight: Float? = null,
    val letterSpacing: Float? = null
) {
    @Stable
    fun merge(other: TextStyle?): TextStyle {
        return if (other == null) {
            this
        } else {
            TextStyle(
                fontWeight = other.fontWeight ?: fontWeight,
                fontSize = other.fontSize ?: fontSize,
                lineHeight = other.lineHeight ?: lineHeight,
                letterSpacing = other.letterSpacing ?: letterSpacing
            )
        }
    }
}