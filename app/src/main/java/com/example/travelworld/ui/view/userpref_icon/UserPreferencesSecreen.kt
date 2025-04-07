package com.example.travelworld.ui.view.userpref_icon

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        LanguageDropdown(
            selectedLanguage = currentLanguage,
            onLanguageSelected = { viewModel.updateLanguage(it) },
            availableLanguages = viewModel.availableLanguages.map { it.first }
        )

        PreferenceItemsSection(
            darkThemeEnabled = darkThemeEnabled,
            notificationEnabled = notificationEnabled,
            onThemeChange = { viewModel.updateDarkTheme(it) },
            onNotificationsChange = { viewModel.updateNotifications(it) }
        )
    }
}

@Composable
private fun PreferenceItemsSection(
    darkThemeEnabled: Boolean,
    notificationEnabled: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onNotificationsChange: (Boolean) -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            PreferenceItem(
                icon = Icons.Default.DarkMode,
                title = "Dark Theme",
                description = "Enable dark mode",
                checked = darkThemeEnabled,
                onCheckedChange = onThemeChange
            )

            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )

            PreferenceItem(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                description = "Enable trip reminders",
                checked = notificationEnabled,
                onCheckedChange = onNotificationsChange
            )
        }
    }
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
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer
            )
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
    val languageDisplay = when (selectedLanguage) {
        "es" -> "Español"
        "en" -> "English"
        "ca" -> "Català"
        "zh" -> "中文"
        else -> selectedLanguage
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Select Language",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = languageDisplay,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp
                        else Icons.Default.ArrowDropDown,
                        contentDescription = "Language dropdown",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.exposedDropdownSize(
                    matchTextFieldWidth = true
                )
            ) {
                availableLanguages.forEach { lang ->
                    val langName = when (lang) {
                        "es" -> "Español"
                        "en" -> "English"
                        "ca" -> "Català"
                        "zh" -> "中文"
                        else -> lang
                    }
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = langName,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        },
                        onClick = {
                            onLanguageSelected(lang)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}