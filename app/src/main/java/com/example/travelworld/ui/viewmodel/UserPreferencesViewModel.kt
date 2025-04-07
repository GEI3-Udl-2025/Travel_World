package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.data.SharedPrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val sharedPrefsManager: SharedPrefsManager
) : ViewModel() {

    // Estado del tema oscuro
    var darkThemeEnabled by mutableStateOf(sharedPrefsManager.darkTheme)
        private set

    // Estado de notificaciones
    var notificationEnabled by mutableStateOf(true)
        private set

    // Estado del idioma - ahora observamos el valor actual
    var language by mutableStateOf(sharedPrefsManager.userLanguage ?: "es")
        private set

    // Lista de idiomas disponibles
    val availableLanguages = listOf(
        "en" to "English",
        "es" to "Español",
        "ca" to "Català",
        "zh" to "中文"
    )

    // Actualizar tema oscuro
    fun updateDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            darkThemeEnabled = enabled
            sharedPrefsManager.darkTheme = enabled
        }
    }

    // Actualizar notificaciones
    fun updateNotifications(enabled: Boolean) {
        viewModelScope.launch {
            notificationEnabled = enabled
        }
    }

    fun updateLanguage(newLanguage: String) {
        viewModelScope.launch {
            sharedPrefsManager.userLanguage = newLanguage
            language = newLanguage
        }
    }

}