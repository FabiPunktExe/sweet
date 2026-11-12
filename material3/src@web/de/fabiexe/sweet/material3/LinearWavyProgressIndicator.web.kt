package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import de.fabiexe.sweet.foundation.layout.*
import de.fabiexe.sweet.ui.DomApplier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toCssString
import js.numbers.JsNumbers.toKotlinDouble
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import web.animations.FrameRequestId
import web.animations.cancelAnimationFrame
import web.animations.requestAnimationFrame
import web.dom.document
import web.html.HTMLDivElement
import web.html.HTMLElement
import web.svg.SVGCircleElement
import web.svg.SVGPathElement
import web.svg.SVGSVGElement
import web.svg.SVG_NAMESPACE
import web.svg.SvgTagName

// FULLY AI GENERATED FILE

private const val containerWidth = 240f
private const val containerHeight = 10f

// One full cycle of the two traveling segments, matching the Material 3 expressive spec.
private const val cycleDurationMs = 1750.0
private const val firstLineHeadDelayMs = 0.0
private const val firstLineHeadDurationMs = 1000.0
private const val firstLineTailDelayMs = 250.0
private const val firstLineTailDurationMs = 1000.0
private const val secondLineHeadDelayMs = 650.0
private const val secondLineHeadDurationMs = 850.0
private const val secondLineTailDelayMs = 900.0
private const val secondLineTailDurationMs = 850.0

// Amplitude changes are animated like in Material 3.
private const val amplitudeDurationMs = 500.0

// M3 emphasized-accelerate easing.
private const val easingX1 = 0.3f
private const val easingY1 = 0.0f
private const val easingX2 = 0.8f
private const val easingY2 = 0.15f

// M3 standard easing.
private const val standardX1 = 0.2f
private const val standardY1 = 0.0f
private const val standardX2 = 0.0f
private const val standardY2 = 1.0f

@Composable
actual fun LinearWavyProgressIndicator(
    modifier: Modifier,
    color: Color,
    trackColor: Color,
    amplitude: Float,
    wavelength: Float,
    waveSpeed: Float,
    strokeWidth: Float,
    gapSize: Float
) {
    val animator = remember { WavyProgressAnimator() }
    WavyProgressIndicatorFrame(animator, modifier) {
        animator.updateIndeterminate(
            color = color,
            trackColor = trackColor,
            amplitude = amplitude,
            wavelength = wavelength,
            waveSpeed = waveSpeed,
            strokeWidth = strokeWidth,
            gapSize = gapSize
        )
    }
}

@Composable
actual fun LinearWavyProgressIndicator(
    progress: Float,
    modifier: Modifier,
    color: Color,
    trackColor: Color,
    amplitude: Float,
    wavelength: Float,
    waveSpeed: Float,
    strokeWidth: Float,
    gapSize: Float,
    stopSize: Float
) {
    val animator = remember { WavyProgressAnimator() }
    WavyProgressIndicatorFrame(animator, modifier) {
        animator.updateDeterminate(
            progress = progress,
            color = color,
            trackColor = trackColor,
            amplitude = amplitude,
            wavelength = wavelength,
            waveSpeed = waveSpeed,
            strokeWidth = strokeWidth,
            gapSize = gapSize,
            stopSize = stopSize
        )
    }
}

@Composable
private fun WavyProgressIndicatorFrame(
    animator: WavyProgressAnimator,
    modifier: Modifier,
    update: () -> Unit
) {
    DisposableEffect(animator) {
        onDispose { animator.stop() }
    }

    val defaultSize = Modifier
        .height(containerHeight)
        .width(minWidth = containerWidth, maxWidth = null)

    ComposeNode<HTMLDivElement, DomApplier>(
        factory = {
            val element = document.createElement("div") as HTMLDivElement
            element.style.position = "relative"
            element.style.overflow = "hidden"
            element.applyModifier(defaultSize then modifier)
            animator.attach(element)
            element
        },
        update = {
            set(modifier) { applyModifier(defaultSize then it) }
        }
    )

    SideEffect {
        update()
        animator.start()
    }
}

/**
 * Drives the wave animation with [requestAnimationFrame] and writes the generated SVG paths
 * directly to the DOM. This avoids a recomposition on every frame.
 */
private class WavyProgressAnimator {
    private var container: HTMLElement? = null
    private var svg: SVGSVGElement? = null
    private var trackPath: SVGPathElement? = null
    private var firstIndicatorPath: SVGPathElement? = null
    private var secondIndicatorPath: SVGPathElement? = null
    private var stopIndicator: SVGCircleElement? = null

    private var frameId: FrameRequestId? = null
    private var running = false
    private var startTime = -1.0

    private var determinate = false
    private var progressValue = 0f

    private var color = Color(0xFF000000)
    private var trackColor = Color(0x00000000)
    private var amplitude = 1f
    private var wavelength = 20f
    private var waveSpeed = 20f
    private var strokeWidth = 4f
    private var gapSize = 4f
    private var stopSize = 4f

    // The amplitude is animated when it changes, e.g. for the determinate indicator in the
    // regions where Material 3 flattens the wave.
    private var currentAmplitude = 0f
    private var amplitudeInitialized = false
    private var amplitudeAnimationFrom = 0f
    private var lastAmplitudeTarget = -1f
    private var amplitudeAnimationStart = -1.0

    private var lastTrackPath = ""
    private var lastFirstPath = ""
    private var lastSecondPath = ""

    fun attach(container: HTMLElement) {
        this.container = container

        val svg: SVGSVGElement = document.createElementNS(SVG_NAMESPACE, SvgTagName.svg)
        svg.setAttribute("width", "100%")
        svg.setAttribute("height", "100%")
        svg.style.position = "absolute"
        svg.style.left = "0"
        svg.style.top = "0"
        svg.style.overflow = "hidden"

        val track = createPathElement()
        val firstIndicator = createPathElement()
        val secondIndicator = createPathElement()
        val stop = document.createElementNS(SVG_NAMESPACE, SvgTagName.circle)
        svg.appendChild(track)
        svg.appendChild(firstIndicator)
        svg.appendChild(secondIndicator)
        svg.appendChild(stop)
        container.appendChild(svg)

        this.svg = svg
        trackPath = track
        firstIndicatorPath = firstIndicator
        secondIndicatorPath = secondIndicator
        stopIndicator = stop
        stop.style.display = "none"
        lastTrackPath = ""
        lastFirstPath = ""
        lastSecondPath = ""
        applyStyle()
    }

    fun updateIndeterminate(
        color: Color,
        trackColor: Color,
        amplitude: Float,
        wavelength: Float,
        waveSpeed: Float,
        strokeWidth: Float,
        gapSize: Float
    ) {
        determinate = false
        stopIndicator?.style?.display = "none"
        updateCommon(color, trackColor, amplitude, wavelength, waveSpeed, strokeWidth, gapSize)
    }

    fun updateDeterminate(
        progress: Float,
        color: Color,
        trackColor: Color,
        amplitude: Float,
        wavelength: Float,
        waveSpeed: Float,
        strokeWidth: Float,
        gapSize: Float,
        stopSize: Float
    ) {
        determinate = true
        progressValue = if (progress.isNaN()) 0f else progress.coerceIn(0f, 1f)
        this.stopSize = max(0f, stopSize)
        updateCommon(color, trackColor, amplitude, wavelength, waveSpeed, strokeWidth, gapSize)
    }

    private fun updateCommon(
        color: Color,
        trackColor: Color,
        amplitude: Float,
        wavelength: Float,
        waveSpeed: Float,
        strokeWidth: Float,
        gapSize: Float
    ) {
        val styleChanged =
            this.color != color || this.trackColor != trackColor || this.strokeWidth != strokeWidth
        this.color = color
        this.trackColor = trackColor
        this.amplitude = amplitude.coerceIn(0f, 1f)
        this.wavelength = max(1f, wavelength)
        this.waveSpeed = max(0f, waveSpeed)
        this.strokeWidth = max(1f, strokeWidth)
        this.gapSize = max(0f, gapSize)
        if (styleChanged) applyStyle()
    }

    fun start() {
        if (running) return
        running = true
        startTime = -1.0
        scheduleFrame()
    }

    fun stop() {
        running = false
        frameId?.let { cancelAnimationFrame(it) }
        frameId = null
    }

    private fun scheduleFrame() {
        frameId = requestAnimationFrame { timestamp -> render(timestamp.toKotlinDouble()) }
    }

    private fun render(timestamp: Double) {
        if (!running) return
        val container = container
        val svg = svg
        if (container == null || svg == null) {
            stop()
            return
        }

        val width = container.clientWidth.toFloat()
        val height = container.clientHeight.toFloat()
        if (width <= 0f || height <= 0f) {
            scheduleFrame()
            return
        }

        if (startTime < 0.0) startTime = timestamp
        val elapsed = timestamp - startTime

        svg.setAttribute("viewBox", "0 0 ${width.format()} ${height.format()}")

        updateAmplitude(timestamp)

        val periodSeconds = if (waveSpeed > 0f) wavelength / waveSpeed else 0f
        val phase = if (periodSeconds > 0f) {
            ((elapsed / 1000.0) / periodSeconds).toFloat() % 1f
        } else {
            0f
        }
        val waveXOffset = -phase * wavelength

        if (determinate) {
            updateDeterminatePaths(width, height, waveXOffset)
        } else {
            val cycle = elapsed % cycleDurationMs
            val firstHead = progress(cycle, firstLineHeadDelayMs, firstLineHeadDurationMs)
            val firstTail = progress(cycle, firstLineTailDelayMs, firstLineTailDurationMs)
            val secondHead = progress(cycle, secondLineHeadDelayMs, secondLineHeadDurationMs)
            val secondTail = progress(cycle, secondLineTailDelayMs, secondLineTailDurationMs)
            updateIndeterminatePaths(width, height, waveXOffset, firstTail, firstHead, secondTail, secondHead)
        }

        scheduleFrame()
    }

    private fun updateAmplitude(timestamp: Double) {
        // Material 3 flattens the wave near 0% and 100% of the determinate indicator.
        val envelope = if (determinate && (progressValue <= 0.1f || progressValue >= 0.95f)) 0f else 1f
        val target = amplitude * envelope

        if (!amplitudeInitialized) {
            currentAmplitude = target
            lastAmplitudeTarget = target
            amplitudeInitialized = true
            amplitudeAnimationStart = -1.0
            return
        }

        if (target != lastAmplitudeTarget) {
            amplitudeAnimationFrom = currentAmplitude
            lastAmplitudeTarget = target
            amplitudeAnimationStart = timestamp
        }

        if (amplitudeAnimationStart < 0.0) return

        val local = ((timestamp - amplitudeAnimationStart) / amplitudeDurationMs).toFloat()
        if (local >= 1f) {
            currentAmplitude = target
            amplitudeAnimationStart = -1.0
            return
        }

        val eased = if (target > amplitudeAnimationFrom) {
            cubicBezierEase(local, standardX1, standardY1, standardX2, standardY2)
        } else {
            cubicBezierEase(local, easingX1, easingY1, easingX2, easingY2)
        }
        currentAmplitude = amplitudeAnimationFrom + (target - amplitudeAnimationFrom) * eased
    }

    private fun updateIndeterminatePaths(
        width: Float,
        height: Float,
        waveXOffset: Float,
        firstTail: Float,
        firstHead: Float,
        secondTail: Float,
        secondHead: Float
    ) {
        val cap = strokeWidth / 2f
        val spacing = gapSize + strokeWidth
        val maxX = max(cap, width - cap)

        val firstTailX = (firstTail * width).coerceIn(cap, maxX)
        val firstHeadX = (firstHead * width).coerceIn(cap, maxX)
        val secondTailX = (secondTail * width).coerceIn(cap, maxX)
        val secondHeadX = (secondHead * width).coerceIn(cap, maxX)

        // The track is a straight line, only the active indicator is wavy. It is drawn around
        // the two traveling segments, right to left, leaving a gap.
        val trackBuilder = StringBuilder()
        val rightStart = firstHeadX + spacing
        if (rightStart < maxX) {
            trackBuilder.append(linePath(rightStart, maxX, height))
        }
        val middleStart = secondHeadX + spacing
        val middleEnd = firstTailX - spacing
        if (middleStart < middleEnd) {
            trackBuilder.append(linePath(middleStart, middleEnd, height))
        }
        val leftEnd = secondTailX - spacing
        if (cap < leftEnd) {
            trackBuilder.append(linePath(cap, leftEnd, height))
        }

        val firstPath = if (firstHeadX - firstTailX > 0.01f) {
            wavePath(firstTailX, firstHeadX, height, waveXOffset)
        } else {
            ""
        }
        val secondPath = if (secondHeadX - secondTailX > 0.01f) {
            wavePath(secondTailX, secondHeadX, height, waveXOffset)
        } else {
            ""
        }

        applyPaths(trackBuilder.toString(), firstPath, secondPath)
    }

    private fun updateDeterminatePaths(width: Float, height: Float, waveXOffset: Float) {
        val cap = strokeWidth / 2f
        val maxX = max(cap, width - cap)
        val progressX = (progressValue * width).coerceIn(cap, maxX)

        // Near zero progress Material 3 reduces the gap so the track does not leave a hole at the
        // beginning of the indicator.
        val barHead = progressValue * width
        val adjustedGap = if (barHead < cap) 0f else min(barHead - cap, gapSize)
        val adaptiveSpacing = adjustedGap + if (barHead >= cap) strokeWidth else 0f

        val trackStart = max(cap, progressX + adaptiveSpacing)
        val track = if (trackStart < maxX) linePath(trackStart, maxX, height) else ""
        val wave = if (progressX - cap > 0.01f) wavePath(cap, progressX, height, waveXOffset) else ""

        applyPaths(track, wave, "")
        updateStopIndicator(width, height)
    }

    private fun updateStopIndicator(width: Float, height: Float) {
        val circle = stopIndicator ?: return

        val inset = strokeWidth / 2f
        var size = min(strokeWidth, stopSize)
        val xOffset = if (size == strokeWidth) 0f else strokeWidth / 4f
        var x = width - size - xOffset
        val progressX = width * progressValue + inset
        if (x <= progressX) {
            size = max(0f, size - (progressX - x))
            x = progressX
        }

        if (size > 0.01f) {
            circle.style.display = ""
            circle.setAttribute("cx", (x + size / 2f).format())
            circle.setAttribute("cy", (height / 2f).format())
            circle.setAttribute("r", (size / 2f).format())
        } else {
            circle.style.display = "none"
        }
    }

    private fun applyPaths(track: String, first: String, second: String) {
        if (track != lastTrackPath) {
            lastTrackPath = track
            trackPath?.setAttribute("d", track)
        }
        if (first != lastFirstPath) {
            lastFirstPath = first
            firstIndicatorPath?.setAttribute("d", first)
        }
        if (second != lastSecondPath) {
            lastSecondPath = second
            secondIndicatorPath?.setAttribute("d", second)
        }
    }

    /** Draws a straight horizontal track segment. */
    private fun linePath(fromX: Float, toX: Float, height: Float): String {
        val centerY = (height / 2f).format()
        return "M${fromX.format()} $centerY L${toX.format()} $centerY"
    }

    /**
     * Samples the quadratic wave between [fromX] and [toX]. The x coordinate is linear in the
     * curve parameter, so the wave can be evaluated directly for any position.
     */
    private fun wavePath(fromX: Float, toX: Float, height: Float, waveXOffset: Float): String {
        if (toX <= fromX) return ""

        val halfWavelength = wavelength / 2f
        val controlAmplitude = currentAmplitude * max(0f, height - strokeWidth)
        val centerY = height / 2f
        val step = min(
            max(1f, (toX - fromX) / 300f),
            max(0.5f, wavelength / 8f)
        )

        val builder = StringBuilder()
        var x = fromX
        var first = true
        while (true) {
            val position = (x - waveXOffset) / halfWavelength
            val segment = floor(position.toDouble()).toInt()
            val t = position - segment
            val sign = if ((segment and 1) == 0) 1f else -1f
            val y = centerY + sign * 2f * t * (1f - t) * controlAmplitude

            if (first) builder.append("M") else builder.append("L")
            builder.append(x.format()).append(' ').append(y.format())
            first = false

            if (x >= toX) break
            x = min(x + step, toX)
        }
        return builder.toString()
    }

    private fun progress(timeMs: Double, delayMs: Double, durationMs: Double): Float {
        val local = (timeMs - delayMs) / durationMs
        return when {
            local <= 0.0 -> 0f
            local >= 1.0 -> 1f
            else -> cubicBezierEase(local.toFloat(), easingX1, easingY1, easingX2, easingY2)
        }
    }

    private fun cubicBezierEase(t: Float, x1: Float, y1: Float, x2: Float, y2: Float): Float {
        var low = 0f
        var high = 1f
        repeat(16) {
            val mid = (low + high) / 2f
            if (bezierCoordinate(mid, x1, x2) < t) low = mid else high = mid
        }
        return bezierCoordinate((low + high) / 2f, y1, y2)
    }

    private fun bezierCoordinate(t: Float, p1: Float, p2: Float): Float {
        val inverse = 1f - t
        return 3f * inverse * inverse * t * p1 +
            3f * inverse * t * t * p2 +
            t * t * t
    }

    private fun applyStyle() {
        trackPath?.applyStroke(trackColor)
        firstIndicatorPath?.applyStroke(color)
        secondIndicatorPath?.applyStroke(color)
        stopIndicator?.setAttribute("fill", color.toCssString())
    }

    private fun SVGPathElement.applyStroke(strokeColor: Color) {
        setAttribute("fill", "none")
        setAttribute("stroke", strokeColor.toCssString())
        setAttribute("stroke-width", strokeWidth.format())
        setAttribute("stroke-linecap", "round")
        setAttribute("stroke-linejoin", "round")
    }

    private fun createPathElement(): SVGPathElement =
        document.createElementNS(SVG_NAMESPACE, SvgTagName.path)
}

private fun Float.format(): String = (round(this * 10f) / 10f).toString()
