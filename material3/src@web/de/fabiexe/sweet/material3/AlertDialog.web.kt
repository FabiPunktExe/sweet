package de.fabiexe.sweet.material3

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.text.TextStyle
import web.cssom.ClassName
import web.dom.ElementId
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.html.HTMLStyleElement

private val scrimColor = Color(0x99000000)
private val titleTextStyle = TextStyle(fontSize = 24f, lineHeight = 32f)
private val textTextStyle = TextStyle(fontSize = 14f, lineHeight = 20f, letterSpacing = 0.25f)

private const val dialogStylesId = "sweet-dialog-styles"

private fun ensureDialogStyles() {
    if (document.getElementById(ElementId(dialogStylesId)) != null) return

    val element = document.createElement("style") as HTMLStyleElement
    element.id = ElementId(dialogStylesId)
    element.textContent = """
        @keyframes sweet-dialog-scrim-in {
            from { background-color: rgba(0, 0, 0, 0.12); }
            to { background-color: rgba(0, 0, 0, 0.6); }
        }
        @keyframes sweet-dialog-content-in {
            from { opacity: 0.2; transform: translateY(10px) scale(0.95); }
            to { opacity: 1; transform: translateY(0) scale(1); }
        }
        .sweet-dialog-scrim {
            animation: sweet-dialog-scrim-in 200ms cubic-bezier(0.25, 0.46, 0.45, 0.94) backwards;
        }
        .sweet-dialog-content {
            animation: sweet-dialog-content-in 200ms cubic-bezier(0.25, 0.46, 0.45, 0.94) backwards;
        }
    """.trimIndent()
    document.head.appendChild(element)
}

@Composable
actual fun AlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    properties: DialogProperties
) {
    val colorScheme = LocalColorScheme.current
    val containerColor = colorScheme.surface
    val iconColor = colorScheme.secondary
    val titleColor = colorScheme.onSurface
    val textColor = colorScheme.onSurfaceVariant

    val containerModifier = if (properties.usePlatformDefaultWidth) {
        Modifier.width(minWidth = 280f, maxWidth = 560f) then modifier
    } else {
        modifier
    }

    var overlayElement by remember { mutableStateOf<HTMLDivElement?>(null) }

    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            ensureDialogStyles()
            val element = document.createElement("div") as HTMLDivElement

            // Constant properties
            element.classList.add(ClassName("sweet-dialog-scrim"))
            element.style.position = "fixed"
            element.style.top = "0"
            element.style.right = "0"
            element.style.bottom = "0"
            element.style.left = "0"
            element.style.zIndex = "1000"
            element.style.boxSizing = "border-box"
            element.style.padding = "24px"
            element.style.display = "flex"
            element.style.alignItems = "center"
            element.style.justifyContent = "center"
            element.style.backgroundColor = scrimColor.toCssString()
            element.setAttribute("role", "dialog")
            element.setAttribute("aria-modal", "true")
            element.tabIndex = -1

            // Dynamic properties
            element.applyDialogEventHandlers(onDismissRequest, properties)

            overlayElement = element
            element
        },
        update = {
            set(onDismissRequest) { applyDialogEventHandlers(it, properties) }
            set(properties) { applyDialogEventHandlers(onDismissRequest, it) }
        }
    ) {
        ComposeNode<HTMLDivElement, DomApplier>(
            factory = {
                val element = document.createElement("div") as HTMLDivElement

                // Constant properties
                element.classList.add(ClassName("sweet-dialog-content"))
                element.style.boxSizing = "border-box"
                element.style.borderRadius = "28px"
                element.style.overflow = "hidden"

                // Dynamic properties
                element.style.backgroundColor = containerColor.toCssString()
                element.applyModifier(containerModifier)

                element
            },
            update = {
                set(containerModifier, HTMLElement::applyModifier)
                set(containerColor) { style.backgroundColor = it.toCssString() }
            }
        ) {
            CompositionLocalProvider(
                LocalBackgroundColor provides containerColor,
                LocalContentColor provides titleColor
            ) {
                Column(Modifier.fillMaxWidth().padding(24f)) {
                    if (icon != null) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CompositionLocalProvider(LocalContentColor provides iconColor) {
                                icon()
                            }
                        }
                    }

                    if (title != null) {
                        CompositionLocalProvider(
                            LocalContentColor provides titleColor,
                            LocalTextStyle provides titleTextStyle
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 16f),
                                horizontalAlignment = if (icon != null) Alignment.Center else Alignment.Start,
                                content = title
                            )
                        }
                    }

                    if (text != null) {
                        CompositionLocalProvider(
                            LocalContentColor provides textColor,
                            LocalTextStyle provides textTextStyle
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 24f),
                                content = text
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End.spacedBy(8f),
                        verticalAlignment = Alignment.Center
                    ) {
                        dismissButton?.invoke()
                        confirmButton()
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        overlayElement?.focus()
    }
}

private fun HTMLElement.applyDialogEventHandlers(
    onDismissRequest: () -> Unit,
    properties: DialogProperties
) {
    onclick = if (properties.dismissOnClickOutside) {
        EventHandler { event -> if (event.target === this) onDismissRequest() }
    } else {
        null
    }
    onkeydown = if (properties.dismissOnBackPress) {
        EventHandler { event ->
            if (event.key == "Escape") {
                event.preventDefault()
                onDismissRequest()
            }
        }
    } else {
        null
    }
}