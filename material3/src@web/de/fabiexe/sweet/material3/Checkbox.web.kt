package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import de.fabiexe.sweet.ui.input.pointer.PointerIcon
import web.dom.document
import web.events.EventHandler
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.html.HTMLInputElement
import web.html.HTMLSpanElement
import web.svg.SVGSVGElement
import web.svg.SVGPathElement
import web.svg.SVG_NAMESPACE
import web.svg.SvgTagName

private const val checkboxSize = 18f
private const val checkboxBorderWidth = 2f
private const val checkboxCornerRadius = 2f
private const val checkboxTouchTarget = 48f
private const val checkboxStateLayerSize = 40f

@Composable
actual fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
    colors: CheckboxColors
) {
    var hovered by remember { mutableStateOf(false) }
    var activeRipples by remember { mutableStateOf(0) }

    val interactive = enabled && onCheckedChange != null

    val containerColor = when {
        !checked -> Color(0x00000000)
        !enabled -> colors.disabledCheckedColor
        else -> colors.checkedColor
    }
    val borderColor = when {
        !enabled -> if (checked) colors.disabledCheckedColor else colors.disabledUncheckedColor
        checked -> colors.checkedColor
        else -> colors.uncheckedColor
    }
    val checkmarkColor = if (enabled) colors.checkmarkColor else colors.disabledCheckmarkColor

    val stateLayerColor = if (checked) colors.checkedColor else colors.uncheckedColor
    // Hidden while a ripple is running, so hover and ripple never overlap.
    val stateLayerBackground = stateLayerColor.copy(
        alpha = if (interactive && hovered && activeRipples == 0) 0.08f else 0f
    )
    val rippleColorCss = stateLayerColor.copy(alpha = 0.12f).toCssString()

    InteractiveComponent {
        ComposeNode<HTMLDivElement, DomApplier>(
            factory = {
                val element = document.createElement("div") as HTMLDivElement

                // Constant properties. The container only provides the 48dp hover area
                // around the box; it is not clickable itself.
                element.style.position = "relative"
                element.style.display = "flex"
                element.style.alignItems = "center"
                element.style.justifyContent = "center"
                element.style.borderRadius = "50%"
                element.style.overflow = "hidden"

                // Dynamic properties
                element.applyModifier(modifier)
                element.applyPointerHoverIcon(modifier, PointerIcon.Default)
                applyCheckboxSize(element)

                element
            },
            update = {
                set(modifier) {
                    applyModifier(it)
                    applyPointerHoverIcon(it, PointerIcon.Default)
                    applyCheckboxSize(this)
                }
            }
        ) {
            // 40dp state layer. It shows the hover color and also contains the ripple, so
            // the ripple is clipped to exactly the same circle as the hover effect.
            ComposeNode<HTMLSpanElement, DomApplier>(
                factory = {
                    val element = document.createElement("span") as HTMLSpanElement
                    element.style.position = "absolute"
                    element.style.left = "50%"
                    element.style.top = "50%"
                    element.style.width = "${checkboxStateLayerSize}px"
                    element.style.height = "${checkboxStateLayerSize}px"
                    element.style.display = "flex"
                    element.style.alignItems = "center"
                    element.style.justifyContent = "center"
                    element.style.borderRadius = "50%"
                    element.style.overflow = "hidden"
                    element.style.transform = "translate(-50%, -50%)"
                    element.style.transition = "background-color 0.1s linear"
                    element.style.backgroundColor = stateLayerBackground.toCssString()
                    element.applyRippleEventHandlers()
                    element
                },
                update = {
                    set(stateLayerBackground) { style.backgroundColor = it.toCssString() }
                }
            ) {
                // The native input provides keyboard support and accessibility. Its default
                // appearance is replaced by the Material 3 box styling.
                ComposeNode<HTMLInputElement, DomApplier>(
                    factory = {
                        val element = document.createElement("input") as HTMLInputElement

                        // Constant properties
                        element.setAttribute("type", "checkbox")
                        element.style.setProperty("appearance", "none")
                        element.style.setProperty("-webkit-appearance", "none")
                        element.style.boxSizing = "border-box"
                        element.style.width = "${checkboxSize}px"
                        element.style.height = "${checkboxSize}px"
                        element.style.margin = "0"
                        element.style.padding = "0"
                        element.style.flexShrink = "0"
                        element.style.borderRadius = "${checkboxCornerRadius}px"
                        element.style.transition =
                            "background-color 0.1s linear, border-color 0.1s linear"

                        // Dynamic properties
                        element.checked = checked
                        element.disabled = !interactive
                        element.style.backgroundColor = containerColor.toCssString()
                        applyCheckboxBorder(element, borderColor)
                        element.onchange = EventHandler { onCheckedChange?.invoke(element.checked) }
                        element.onmouseenter = EventHandler { hovered = true }
                        element.onmouseleave = EventHandler { hovered = false }
                        element.applyCheckboxPressHandler(
                            interactive,
                            rippleColorCss,
                            onRippleStart = { activeRipples++ },
                            onRippleEnd = { activeRipples-- }
                        )

                        element
                    },
                    update = {
                        set(checked) { if (this.checked != it) this.checked = it }
                        set(interactive) {
                            disabled = !it
                            applyCheckboxPressHandler(
                                it,
                                rippleColorCss,
                                onRippleStart = { activeRipples++ },
                                onRippleEnd = { activeRipples-- }
                            )
                        }
                        set(containerColor) { style.backgroundColor = it.toCssString() }
                        set(borderColor) { applyCheckboxBorder(this, it) }
                        set(onCheckedChange) { callback ->
                            val field = this
                            onchange = EventHandler { callback?.invoke(field.checked) }
                        }
                        set(rippleColorCss) {
                            applyCheckboxPressHandler(
                                interactive,
                                it,
                                onRippleStart = { activeRipples++ },
                                onRippleEnd = { activeRipples-- }
                            )
                        }
                    }
                )

                // Checkmark as an SVG path, matching the geometry used by Compose Material 3.
                ComposeNode<SVGSVGElement, DomApplier>(
                    factory = {
                        val element = document.createElementNS(SVG_NAMESPACE, SvgTagName.svg)
                        element.setAttribute("viewBox", "0 0 ${checkboxSize.toInt()} ${checkboxSize.toInt()}")
                        element.setAttribute("width", "${checkboxSize.toInt()}")
                        element.setAttribute("height", "${checkboxSize.toInt()}")
                        element.style.position = "absolute"
                        element.style.left = "50%"
                        element.style.top = "50%"
                        element.style.transform = "translate(-50%, -50%)"
                        element.style.pointerEvents = "none"
                        element.style.overflow = "visible"
                        element.style.transition = "opacity 0.1s linear"
                        element.style.opacity = if (checked) "1" else "0"
                        element
                    },
                    update = {
                        set(checked) { style.opacity = if (it) "1" else "0" }
                    }
                ) {
                    ComposeNode<SVGPathElement, DomApplier>(
                        factory = {
                            val element = document.createElementNS(SVG_NAMESPACE, SvgTagName.path)
                            element.setAttribute(
                                "d",
                                "M${checkboxSize * 0.2} ${checkboxSize * 0.5}" +
                                    "L${checkboxSize * 0.4} ${checkboxSize * 0.7}" +
                                    "L${checkboxSize * 0.8} ${checkboxSize * 0.3}"
                            )
                            element.setAttribute("fill", "none")
                            element.setAttribute("stroke-width", "${checkboxBorderWidth}")
                            element.setAttribute("stroke-linecap", "square")
                            element.setAttribute("stroke-linejoin", "miter")
                            applyCheckmarkColor(element, checkmarkColor)
                            element
                        },
                        update = {
                            set(checkmarkColor) { applyCheckmarkColor(this, it) }
                        }
                    )
                }
            }
        }
    }
}

private fun applyCheckboxSize(element: HTMLElement) {
    // A container with only absolutely positioned children would collapse under
    // `fit-content`, which is what applyModifier sets.
    element.style.width = "${checkboxTouchTarget}px"
    element.style.height = "${checkboxTouchTarget}px"
}

/**
 * Attaches the press handler to the input, so only clicks on the box itself start a ripple.
 * The ripple is appended to the 40dp state layer, because an `<input>` cannot have children.
 */
private fun HTMLInputElement.applyCheckboxPressHandler(
    enabled: Boolean,
    rippleColorCss: String,
    onRippleStart: () -> Unit,
    onRippleEnd: () -> Unit
) {
    onpointerdown = EventHandler { event ->
        if (enabled) {
            val stateLayer = parentElement
            if (stateLayer != null) {
                onRippleStart()
                stateLayer.createRipple(rippleColorCss, event) { onRippleEnd() }
            }
        }
    }
}

private fun applyCheckboxBorder(element: HTMLInputElement, color: Color) {
    element.style.border = "${checkboxBorderWidth}px solid ${color.toCssString()}"
}

private fun applyCheckmarkColor(element: SVGPathElement, color: Color) {
    element.setAttribute("stroke", color.toCssString())
}
