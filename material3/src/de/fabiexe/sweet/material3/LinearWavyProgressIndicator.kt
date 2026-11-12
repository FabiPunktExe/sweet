package de.fabiexe.sweet.material3

import androidx.compose.runtime.Composable
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
expect fun LinearWavyProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = currentColorScheme().primary,
    trackColor: Color = currentColorScheme().secondaryContainer,
    amplitude: Float = 1f,
    wavelength: Float = 20f,
    waveSpeed: Float = wavelength,
    strokeWidth: Float = 4f,
    gapSize: Float = 4f
)

@Composable
expect fun LinearWavyProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = currentColorScheme().primary,
    trackColor: Color = currentColorScheme().secondaryContainer,
    amplitude: Float = 1f,
    wavelength: Float = 40f,
    waveSpeed: Float = wavelength,
    strokeWidth: Float = 4f,
    gapSize: Float = 4f,
    stopSize: Float = 4f
)