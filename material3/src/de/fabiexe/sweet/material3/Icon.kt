package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.foundation.layout.FillElement
import de.fabiexe.sweet.foundation.layout.SizeElement
import de.fabiexe.sweet.foundation.layout.size
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

const val DefaultIconSize = 24f

@Composable
expect fun Icon(
    resource: DrawableResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = currentContentColor()
)

internal fun Modifier.withDefaultIconSize(): Modifier {
    val hasSize = fold(false) { acc, element -> acc || element is SizeElement || element is FillElement }
    return if (hasSize) this else size(DefaultIconSize)
}