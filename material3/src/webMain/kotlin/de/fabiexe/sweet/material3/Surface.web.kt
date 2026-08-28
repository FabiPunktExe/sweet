package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.CompositionLocalProvider
import de.fabiexe.sweet.foundation.layout.applyModifier
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import web.dom.document
import web.html.HTMLDivElement

@Composable
actual fun Surface(
    modifier: Modifier,
    color: Color,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val modifier = Modifier.fillMaxSize(1f) then modifier
        ComposeNode<HTMLDivElement, DomApplier>(
            factory = {
                val element = document.createElement("div") as HTMLDivElement
                element.applyModifier(modifier)
                element.style.backgroundColor = color.toCssString()
                element
            },
            update = {
                set(modifier) {
                    applyModifier(it)
                    style.backgroundColor = color.toCssString()
                }
                set(color) {
                    applyModifier(modifier)
                    style.backgroundColor = it.toCssString()
                }
            },
            content = content
        )
    }
}