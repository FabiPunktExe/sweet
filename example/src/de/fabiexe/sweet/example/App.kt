package de.fabiexe.sweet.example

import androidx.compose.runtime.*
import de.fabiexe.sweet.foundation.layout.Arrangement
import de.fabiexe.sweet.foundation.layout.Column
import de.fabiexe.sweet.foundation.layout.fillMaxSize
import de.fabiexe.sweet.foundation.layout.fillMaxWidth
import de.fabiexe.sweet.foundation.layout.padding
import de.fabiexe.sweet.material3.*
import de.fabiexe.sweet.ui.Alignment
import de.fabiexe.sweet.ui.Modifier
import de.fabiexe.sweet.ui.graphics.Color

private val drawerPages = listOf("Home", "Profile", "Settings", "About")

@Composable
fun App() {
    Theme(colorScheme(Color(0xAAFF88))) {
        val drawerOpen = remember { mutableStateOf(false) }
        val drawerPermanent = remember { mutableStateOf(false) }
        val selectedPage = remember { mutableStateOf(0) }

        if (drawerPermanent.value) {
            PermanentNavigationDrawer(
                drawerContent = { DrawerContent(drawerOpen, selectedPage) }
            ) {
                AppContent(drawerOpen, drawerPermanent)
            }
        } else {
            ModalNavigationDrawer(
                drawerContent = { DrawerContent(drawerOpen, selectedPage) },
                open = drawerOpen.value,
                onClose = { drawerOpen.value = false }
            ) {
                AppContent(drawerOpen, drawerPermanent)
            }
        }
    }
}

@Composable
fun DrawerContent(drawerOpen: MutableState<Boolean>, selectedPage: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12f, bottom = 12f),
        verticalArrangement = Arrangement.Start.spacedBy(8f)
    ) {
        drawerPages.forEachIndexed { index, page ->
            NavigationDrawerItem(
                label = { Text(page) },
                selected = selectedPage.value == index,
                onClick = {
                    selectedPage.value = index
                    drawerOpen.value = false
                }
            )
        }
    }
}

@Composable
fun AppContent(drawerOpen: MutableState<Boolean>, drawerPermanent: MutableState<Boolean>) {
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
            Button(onClick = { drawerOpen.value = true }, enabled = !drawerPermanent.value) {
                Text("Open drawer")
            }
            Button(onClick = { drawerPermanent.value = !drawerPermanent.value }) {
                Text(if (drawerPermanent.value) "Switch to modal drawer" else "Switch to permanent drawer")
            }
        }
    }
}