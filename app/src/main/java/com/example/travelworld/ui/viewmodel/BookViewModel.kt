package com.example.travelworld.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Hotel
import com.example.travelworld.domain.model.ReserveRequest
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.repo.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val hotelRepo: HotelRepository
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _uiState = MutableStateFlow(BookUiState())
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<BookUiState> = _uiState

    val cities = listOf("London", "Paris", "Barcelona")
    val groupId = "G08" // O cámbialo por tu groupId

    @RequiresApi(Build.VERSION_CODES.O)
    fun toggleCityMenu() = _uiState.update { it.copy(cityMenu = !it.cityMenu) }
    @RequiresApi(Build.VERSION_CODES.O)
    fun selectCity(c: String) = _uiState.update { it.copy(city = c, cityMenu = false) }
    @RequiresApi(Build.VERSION_CODES.O)
    fun pickStart(d: LocalDate) = _uiState.update { it.copy(startDate = d) }
    @RequiresApi(Build.VERSION_CODES.O)
    fun pickEnd(d: LocalDate) = _uiState.update { it.copy(endDate = d) }

    @RequiresApi(Build.VERSION_CODES.O)
    fun search() = viewModelScope.launch {
        val s = _uiState.value.startDate ?: return@launch
        val e = _uiState.value.endDate ?: return@launch
        val fmt = DateTimeFormatter.ISO_DATE
        val city = _uiState.value.city

        _uiState.update { it.copy(loading = true, message = null) }

        try {
            val hotels = hotelRepo.getAvailability(groupId, s.format(fmt), e.format(fmt), city = city)
            _uiState.update { it.copy(loading = false, hotels = hotels) }
        } catch (e: Exception) {
            _uiState.update { it.copy(loading = false, hotels = emptyList(), message = e.localizedMessage ?: "Error") }
        }
    }

    // Reservar
    fun reserve(req: ReserveRequest, onResult: (Reservation?) -> Unit) {
        viewModelScope.launch {
            try {
                val res = hotelRepo.reserve(groupId, req)
                onResult(res)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
}

data class BookUiState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val loading: Boolean = false,
    val cityMenu: Boolean = false,
    val city: String = "London",
    val startDate: LocalDate? = LocalDate.now(),
    val endDate: LocalDate? = LocalDate.now().plusDays(2),
    val hotels: List<Hotel> = emptyList(),
    val message: String? = null
)
