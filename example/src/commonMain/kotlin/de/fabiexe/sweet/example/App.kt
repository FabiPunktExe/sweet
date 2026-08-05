package de.fabiexe.sweet.example

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.Arrangement
import de.fabiexe.sweet.foundation.layout.Column
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.material3.Button
import de.fabiexe.sweet.material3.SweetTheme
import de.fabiexe.sweet.material3.Text
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
fun App() {
    SweetTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Center,
            verticalArrangement = Arrangement.Center
        ) {
            var count by remember { mutableStateOf(0) }
            Button(onClick = { count++ }) {
                Text("Clicked $count times")
            }
        }
    }
}
