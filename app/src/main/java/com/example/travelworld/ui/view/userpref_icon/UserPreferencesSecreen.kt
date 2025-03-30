package com.example.travelworld.ui.view.userpref_icon

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelworld.ui.viewmodel.UserPreferencesViewModel

@Composable
fun UserPreferencesApp(
    viewModel: UserPreferencesViewModel = hiltViewModel()
) {
    val darkThemeEnabled = viewModel.darkThemeEnabled
    val notificationEnabled = viewModel.notificationEnabled
    val currentLanguage = viewModel.language

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Language Preference
        LanguageDropdown(
            selectedLanguage = currentLanguage,
            onLanguageSelected = { viewModel.updateLanguage(it) },
            availableLanguages = viewModel.availableLanguages.map { it.first }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Theme Preference
        PreferenceItem(
            icon = Icons.Default.DarkMode,
            title = "Dark Theme",
            description = "Enable dark mode",
            checked = darkThemeEnabled,
            onCheckedChange = { viewModel.updateDarkTheme(it) }
        )

        // Notifications Preference
        PreferenceItem(
            icon = Icons.Default.Notifications,
            title = "Notifications",
            description = "Enable trip reminders",
            checked = notificationEnabled,
            onCheckedChange = { viewModel.updateNotifications(it) }
        )
    }
}

@Preview
@Composable
fun UserPreferencesAppPreview() {
    UserPreferencesApp()
}

@Composable
fun PreferenceItem(
    icon: ImageVector,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    availableLanguages: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    // Map language codes to display names
    val languageDisplay = when (selectedLanguage) {
        "es" -> "Español"
        "en" -> "English"
        "fr" -> "Français"
        "de" -> "Deutsch"
        "ca" -> "Català"
        else -> selectedLanguage
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Language,
            contentDescription = "Language",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Language", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "App display language",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Dropdown implementation
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = languageDisplay,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                availableLanguages.forEach { lang ->
                    val langName = when (lang) {
                        "es" -> "Español"
                        "en" -> "English"
                        "fr" -> "Français"
                        "de" -> "Deutsch"
                        "ca" -> "Català"
                        else -> lang
                    }
                    DropdownMenuItem(
                        text = { Text(langName) },
                        onClick = {
                            onLanguageSelected(lang)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}