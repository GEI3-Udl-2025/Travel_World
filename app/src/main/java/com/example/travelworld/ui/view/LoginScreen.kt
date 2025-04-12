package com.example.travelworld.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelworld.R

@Composable
fun LoginScreen(navController: NavController) {

    // States for username, password, and the alert dialog
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showAlert by remember { mutableStateOf(false) }

    // Get default values from strings.xml
    val defaultUser = stringResource(id = R.string.default_user)
    val defaultPass = stringResource(id = R.string.default_pass)

    username = defaultUser
    password = defaultPass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.login_screen), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { stringResource(id = R.string.username) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { stringResource(id = R.string.password) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (username == defaultUser && password == defaultPass) {
                    // Navigate to Home and remove Login from the back stack
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    showAlert = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }

    // Show an alert dialog if the login fails
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Login Failed") },
            text = { Text("Invalid username or password.") },
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text(stringResource(id = R.string.ok))
                }
            }
        )
    }
}