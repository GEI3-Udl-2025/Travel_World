package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Enum para representar las pantallas principales
enum class MainScreen {
    HOME, TRIP, ITINERARY, USER_PREFERENCE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    // Estado que guarda la pantalla actual
    var selectedScreen by remember { mutableStateOf(MainScreen.HOME) }
    var showSettingsMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Travel App") },
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
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedScreen == MainScreen.HOME,
                    onClick = { selectedScreen = MainScreen.HOME },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Flight, contentDescription = "Trip") },
                    selected = selectedScreen == MainScreen.TRIP,
                    onClick = { selectedScreen = MainScreen.TRIP },
                    label = { Text("Trip") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Itinerary") },
                    selected = selectedScreen == MainScreen.ITINERARY,
                    onClick = { selectedScreen = MainScreen.ITINERARY },
                    label = { Text("Itinerary") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "User Preference") },
                    selected = selectedScreen == MainScreen.USER_PREFERENCE,
                    onClick = { selectedScreen = MainScreen.USER_PREFERENCE },
                    label = { Text("Preferences") }
                )
            }
        }
    ) { innerPadding ->
        // Contenido que se actualiza según la pantalla seleccionada
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedScreen) {
                MainScreen.HOME -> MenuScreen()
                MainScreen.TRIP -> TripScreen()
                MainScreen.ITINERARY -> ItineraryScreen()
                MainScreen.USER_PREFERENCE -> UserPreferenceScreen()
            }
        }
    }
}

@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun TripScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Trip Screen", style = MaterialTheme.typography.headlineMedium)

    }
}

@Composable
fun ItineraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Itinerary Screen", style = MaterialTheme.typography.headlineMedium)

    }
}

@Composable
fun UserPreferenceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User Preference Screen", style = MaterialTheme.typography.headlineMedium)
        // Aquí puedes agregar los componentes y lógica de la pantalla de preferencias de usuario
    }
}

@Preview
@Composable
fun MainAppPreview() {
    MenuScreen(navController = rememberNavController())
}