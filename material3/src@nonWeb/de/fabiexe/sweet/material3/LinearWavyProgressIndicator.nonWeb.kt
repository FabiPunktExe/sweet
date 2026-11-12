package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import de.fabiexe.sweet.foundation.layout.toAndroidxModifier
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color
import de.fabiexe.sweet.ui.graphics.toAndroidxColor
import androidx.compose.material3.LinearWavyProgressIndicator as AndroidxLinearWavyProgressIndicator

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
    val strokeWidthPx = with(LocalDensity.current) { strokeWidth.dp.toPx() }
    AndroidxLinearWavyProgressIndicator(
        modifier = modifier.toAndroidxModifier(),
        color = color.toAndroidxColor(),
        trackColor = trackColor.toAndroidxColor(),
        stroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
        trackStroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
        gapSize = gapSize.dp,
        amplitude = amplitude,
        wavelength = wavelength.dp,
        waveSpeed = waveSpeed.dp
    )
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
    val strokeWidthPx = with(LocalDensity.current) { strokeWidth.dp.toPx() }
    AndroidxLinearWavyProgressIndicator(
        progress = { progress },
        modifier = modifier.toAndroidxModifier(),
        color = color.toAndroidxColor(),
        trackColor = trackColor.toAndroidxColor(),
        stroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
        trackStroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
        gapSize = gapSize.dp,
        stopSize = stopSize.dp,
        amplitude = { value -> amplitude * if (value <= 0.1f || value >= 0.95f) 0f else 1f },
        wavelength = wavelength.dp,
        waveSpeed = waveSpeed.dp
    )
}