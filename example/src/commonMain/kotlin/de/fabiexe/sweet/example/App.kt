package de.fabiexe.sweet.example

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.Arrangement
import de.fabiexe.sweet.foundation.layout.Column
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.material3.Button
import de.fabiexe.sweet.material3.Theme
import de.fabiexe.sweet.material3.Text
import de.fabiexe.sweet.material3.TextField
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier

@Composable
fun App() {
    Theme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Center,
            verticalArrangement = Arrangement.Center
        ) {
            var count by remember { mutableStateOf(0) }
            var name by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") }
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            TextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Message") },
                singleLine = false
            )
            Button(onClick = { count++ }) {
                Text("Clicked $count times")
            }
            Button(onClick = { name = ""; message = "" }) {
                Text("Clear")
            }
        }
    }
}