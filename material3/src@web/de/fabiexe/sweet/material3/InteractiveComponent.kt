package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import de.fabiexe.sweet.ui.DomApplier
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.window.window

// Material's compact window size class (phones).
private const val compactMaxWidth = 600

@Composable
fun InteractiveComponent(content: @Composable () -> Unit) {
    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.style.display = "flex"
            element.style.justifyContent = "center"
            element.style.alignItems = "center"
            element.style.minHeight = "48px"

            element.onclick = EventHandler { event ->
                if (event.target == element && window.innerWidth <= compactMaxWidth) {
                    (element.firstElementChild as? HTMLElement)?.click()
                }
            }

            element
        },
        update = {},
        content = content
    )
}
