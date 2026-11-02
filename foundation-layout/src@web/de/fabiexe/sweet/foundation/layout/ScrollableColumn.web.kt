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
actual fun ScrollableColumn(
    modifier: Modifier,
    verticalArrangement: Arrangement,
    horizontalAlignment: Alignment,
    content: @Composable () -> Unit
) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.overflowY = "auto"
            element.style.overflowX = "hidden"
            element.applyModifier(modifier)
            element
        },
        update = {
            set(modifier, HTMLElement::applyModifier)
        },
        content = {
            ComposeNode<HTMLDivElement, DomApplier>(
                factory = {
                    val element = document.createElement("div") as HTMLDivElement
                    element.style.display = "flex"
                    element.style.flexDirection = "column"
                    element.style.width = "100%"
                    element.style.minHeight = "100%"
                    element.applyArrangement(verticalArrangement)
                    element.applyAlignment(horizontalAlignment)
                    element
                },
                update = {
                    set(verticalArrangement, HTMLDivElement::applyArrangement)
                    set(horizontalAlignment, HTMLDivElement::applyAlignment)
                },
                content = content
            )
        }
    )
}