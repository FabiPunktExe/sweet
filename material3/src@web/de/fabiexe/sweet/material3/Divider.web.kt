package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.document
import web.html.HTMLDivElement

@Composable
actual fun HorizontalDivider(modifier: Modifier, thickness: Float, color: Color) {
    val defaultSize = Modifier.fillMaxWidth().height(thickness)
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.style.flexShrink = "0"
            element.style.pointerEvents = "none"

            // Dynamic properties
            element.applyModifier(defaultSize then modifier)
            element.style.backgroundColor = color.toCssString()

            element
        },
        update = {
            set(modifier) { applyModifier(defaultSize then it) }
            set(thickness) { applyModifier(Modifier.fillMaxWidth().height(it) then modifier) }
            set(color) { style.backgroundColor = it.toCssString() }
        }
    )
}

@Composable
actual fun VerticalDivider(modifier: Modifier, thickness: Float, color: Color) {
    val defaultSize = Modifier.fillMaxHeight().width(thickness)
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.style.flexShrink = "0"
            element.style.alignSelf = "stretch"
            element.style.pointerEvents = "none"

            // Dynamic properties
            element.applyModifier(defaultSize then modifier)
            element.style.backgroundColor = color.toCssString()

            element
        },
        update = {
            set(modifier) { applyModifier(defaultSize then it) }
            set(thickness) { applyModifier(Modifier.fillMaxHeight().width(it) then modifier) }
            set(color) { style.backgroundColor = it.toCssString() }
        }
    )
}
