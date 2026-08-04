package de.fabiexe.sweet.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.applyAlignment
import web.dom.document
import web.html.HTMLDivElement
import web.html.HTMLElement

@Composable
actual fun Box(
    modifier: Modifier,
    horizontalAlignment: Alignment,
    verticalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.display = "flex"
            element.applyArrangement(horizontalAlignment.toArrangement())
            element.applyAlignment(verticalAlignment)
            element.applyModifier(modifier)
            element
        },
        update = {
            set(horizontalAlignment) { applyArrangement(it.toArrangement()) }
            set(verticalAlignment, HTMLDivElement::applyAlignment)
            set(modifier, HTMLElement::applyModifier)
        },
        content = content
    )
}