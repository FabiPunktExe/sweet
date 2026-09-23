package de.fabiexe.sweet.example

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.Arrangement
import de.fabiexe.sweet.foundation.layout.Column
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.material3.*
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

@Composable
fun App() {
    Theme(colorScheme(Color(0xAAFF88))) {
        Surface {
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
                Text("Test")
                Text("Test")
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Message") },
                    singleLine = false
                )
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Name") },
                    singleLine = false
                )
                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Message 3") },
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
}