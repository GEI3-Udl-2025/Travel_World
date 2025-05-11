package com.example.travelworld.ui.view.settings_option

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelworld.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.about))},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = stringResource(id = R.string.about_screen), style = MaterialTheme.typography.headlineMedium)
            Text(text = "Authors: Xiao Long Ji and Jan Castells Sanllehi")
            Text(
                text = "Your perfect companion for planning unforgettable trips. Organize itineraries, discover destinations and enjoy every adventure.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            // Agrega más elementos básicos
            Divider(thickness = 1.dp)
            Text(text = "Versión: 0.4.2")
            Text(text = "Contacto: contact@example.com")
        }
    }
}
