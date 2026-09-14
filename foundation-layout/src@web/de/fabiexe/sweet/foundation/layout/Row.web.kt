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
actual fun Row(
    modifier: Modifier,
    horizontalArrangement: Arrangement,
    verticalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.display = "flex"
            element.style.flexDirection = "row"
            element.applyArrangement(horizontalArrangement)
            element.applyAlignment(verticalAlignment)
            element.applyModifier(modifier)
            element
        },
        update = {
            set(horizontalArrangement, HTMLDivElement::applyArrangement)
            set(verticalAlignment, HTMLDivElement::applyAlignment)
            set(modifier, HTMLElement::applyModifier)
        },
        content = content
    )
}