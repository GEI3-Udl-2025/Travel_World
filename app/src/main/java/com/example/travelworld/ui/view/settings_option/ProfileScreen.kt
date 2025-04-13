package com.example.travelworld.ui.view.settings_option



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelworld.R

@Composable
fun ProfileScreen(navController: NavController, userId: Int?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.profile_screen) + " - UserId: $userId")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            // Navigate back to Home
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text(stringResource(id = R.string.back_to_home))
        }
    }
}