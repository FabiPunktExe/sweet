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
actual fun Column(
    modifier: Modifier,
    verticalArrangement: Arrangement,
    horizontalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.display = "flex"
            element.style.flexDirection = "column"
            element.applyArrangement(verticalArrangement)
            element.applyAlignment(horizontalAlignment)
            element.applyPadding(modifier)
            element
        },
        update = {
            set(verticalArrangement, HTMLDivElement::applyArrangement)
            set(horizontalAlignment, HTMLDivElement::applyAlignment)
            set(modifier, HTMLElement::applyPadding)
        },
        content = content
    )
}