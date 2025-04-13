package com.example.travelworld.ui.view

import HomeApp
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelworld.R
import com.example.travelworld.ui.view.itinerary_icon.TripAppv1
import com.example.travelworld.ui.view.trip_icon.TripApp
import com.example.travelworld.ui.view.userpref_icon.UserPreferencesApp

// Enum para representar las pantallas principales
enum class TravelMode {
    HOME, TRIP, ITINERARY, USER_PREFERENCE
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelApp(navController: NavController) {
    // Estado que guarda la pantalla actual
    var selectedScreen by remember { mutableStateOf(TravelMode.TRIP) }
    var showSettingsMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home_title)) },
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
                                text = { Text(text = stringResource(id = R.string.about)) },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Build, contentDescription = "Version Icon") },
                                text = { Text(stringResource(id = R.string.version))},
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("version")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Profile Icon") },
                                text = { Text(stringResource(id = R.string.profile)) },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("profile")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = "Settings Icon") },
                                text = { Text(stringResource(id = R.string.settings)) },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("settings")
                                }
                            )
                            DropdownMenuItem(
                                leadingIcon = {Icon (Icons.Filled.Policy, contentDescription = "Terms & Conditions") },
                                text = { Text(stringResource(id = R.string.terms_conditions)) },
                                onClick = {
                                    showSettingsMenu = false
                                    navController.navigate("terms")
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
                    icon = { Icon(Icons.Default.Flight, contentDescription = "Trip") },
                    selected = selectedScreen == TravelMode.TRIP,
                    onClick = { selectedScreen = TravelMode.TRIP },
                    label = { Text(stringResource(id = R.string.trip))}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedScreen == TravelMode.HOME,
                    onClick = { selectedScreen = TravelMode.HOME },
                    label = { Text(stringResource(id = R.string.home)) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Itinerary") },
                    selected = selectedScreen == TravelMode.ITINERARY,
                    onClick = { selectedScreen = TravelMode.ITINERARY },
                    label = { Text(stringResource(id = R.string.itinerary)) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "User Preference") },
                    selected = selectedScreen == TravelMode.USER_PREFERENCE,
                    onClick = { selectedScreen = TravelMode.USER_PREFERENCE },
                    label = { Text(stringResource(id = R.string.user_preference)) }
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
                TravelMode.TRIP -> TripScreen(navController)
                TravelMode.HOME -> HomeScreen()
                TravelMode.ITINERARY -> ItineraryScreen()
                TravelMode.USER_PREFERENCE -> UserPreferenceScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(id = R.string.trip_screen), style = MaterialTheme.typography.titleLarge)
        TripApp(navController = navController)
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(id = R.string.home_screen), style = MaterialTheme.typography.titleLarge)
        HomeApp()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItineraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(id = R.string.itinerary_screen), style = MaterialTheme.typography.titleLarge)
        TripAppv1()
    }
}

@Composable
fun UserPreferenceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = stringResource(id = R.string.user_preference_screen), style = MaterialTheme.typography.titleLarge)
        UserPreferencesApp()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainAppPreview() {
    TravelApp(navController = rememberNavController())
}