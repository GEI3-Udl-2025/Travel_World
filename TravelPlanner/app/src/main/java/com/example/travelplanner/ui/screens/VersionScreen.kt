package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VersionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Version") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back Icon"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Version Screen", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Aplicación: TravelWorld")
            Text(text = "Versión actual: 0.0.1")
            Text(text = "Fecha de lanzamiento: Febrero 2025")
            Divider(thickness = 1.dp)
            Text(text = "Changelog:")
            Text(text = "- Home screen was created")
            Text(text = "- About screen was created")
            Text(text = "- Terms & Conditions screen was created")
            Text(text = "- Profile screen was created")
            Text(text = "- Login screen was created")
            Text(text = "- Itinerary screen was created")
            Text(text = "- Setting screen was created")
        }
    }
}