package de.fabiexe.sweet.material3

import js.array.asList
import web.dom.document
import web.events.EventHandler
import web.pointer.PointerEvent
import web.html.HTMLSpanElement
import web.html.HTMLElement
import kotlin.math.hypot

private const val rippleExpandDuration = 400
private const val rippleFadeDuration = 200
private const val rippleExpandEasing = "cubic-bezier(0.2, 0, 0, 1)"

private const val rippleMarker = "sweet-ripple"
private const val rippleFadingMarker = "sweet-ripple-fading"

private const val rippleExpandTransition = "transform ${rippleExpandDuration}ms $rippleExpandEasing"
private const val rippleFadeTransition = "$rippleExpandTransition, opacity ${rippleFadeDuration}ms linear"

internal fun HTMLElement.createRipple(rippleColorCss: String, event: PointerEvent) {
    val rect = getBoundingClientRect()
    val x = event.clientX - rect.left
    val y = event.clientY - rect.top

    val cornerDistance = maxOf(
        hypot(x, y),
        hypot(rect.width - x, y),
        hypot(x, rect.height - y),
        hypot(rect.width - x, rect.height - y)
    )
    val diameter = cornerDistance * 2

    val ripple = document.createElement("span") as HTMLSpanElement
    ripple.setAttribute(rippleMarker, "")
    ripple.style.position = "absolute"
    ripple.style.width = "${diameter}px"
    ripple.style.height = "${diameter}px"
    ripple.style.left = "${x - diameter / 2}px"
    ripple.style.top = "${y - diameter / 2}px"
    ripple.style.borderRadius = "50%"
    ripple.style.pointerEvents = "none"
    ripple.style.backgroundColor = rippleColorCss
    ripple.style.opacity = "1"
    ripple.style.transform = "scale(0)"
    ripple.style.transition = rippleExpandTransition
    appendChild(ripple)

    ripple.ontransitionend = EventHandler { event ->
        if (event.propertyName == "opacity") {
            removeChild(ripple)
        }
    }

    ripple.offsetWidth
    ripple.style.transform = "scale(1)"
}

internal fun HTMLElement.applyRippleEventHandlers() {
    val fadeOutRipples = EventHandler {
        for (child in children.asList()) {
            val ripple = child as? HTMLSpanElement ?: continue
            if (ripple.getAttribute(rippleMarker) == null) continue
            if (ripple.getAttribute(rippleFadingMarker) != null) continue

            ripple.setAttribute(rippleFadingMarker, "")
            ripple.style.transition = rippleFadeTransition
            ripple.style.opacity = "0"
        }
    }
    onpointerup = fadeOutRipples
    onpointercancel = fadeOutRipples
    onpointerleave = fadeOutRipples
}