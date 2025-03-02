package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

enum class IndexMode {
    MENU, ITINIERARIO, EXPLORAR
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenScaffold2(navController: NavController) {
    var showSettingsMenu by remember { mutableStateOf(false) }

    // Estado para el elemento seleccionado del bottom app bar
    var selectedIndex by remember { mutableStateOf(IndexMode.MENU) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Screen") },
                actions = {
                    Box {
                        IconButton(onClick = { showSettingsMenu = !showSettingsMenu }) {
                            Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                        }
                        DropdownMenu(
                            expanded = showSettingsMenu,
                            onDismissRequest = { showSettingsMenu = false }
                        ) {
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "About Icon") },
                                text = { Text("About") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Build, contentDescription = "Version Icon") },
                                text = { Text("Version") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("version")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Profile Icon") },
                                text = { Text("Profile") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("profile")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = "Settings Icon") },
                                text = { Text("Settings") },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("settings")
                                }
                            )
                        }
                    }
                }
            )

        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menú") },
                    selected = selectedIndex == IndexMode.MENU,
                    onClick = { selectedIndex == IndexMode.MENU },
                    label = { Text("Menú") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.FormatListNumbered, contentDescription = "itinerarios") },
                    selected = selectedIndex == IndexMode.ITINIERARIO,
                    onClick = { navController.navigate("itinerarios") },
                    label = { Text("Itinerarios") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Explore, contentDescription = "Explorar") },
                    selected = selectedIndex == IndexMode.EXPLORAR,
                    onClick = { selectedIndex == IndexMode.EXPLORAR },
                    label = { Text("Explorar") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción del FAB */ }
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "FAB Icon")
            }
        },
        content = { padding ->
            // Contenido de la pantalla
            Column(modifier = Modifier.padding(padding).fillMaxSize()) {
                // Aquí puedes agregar más contenido
                Button(
                    onClick = {
                        navController.navigate("addTrip")
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text("Agregar Nuevo Viaje")
                }
            }
        }
    )
}
