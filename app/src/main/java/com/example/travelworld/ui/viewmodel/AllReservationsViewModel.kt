package com.example.travelworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.repo.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllReservationsViewModel @Inject constructor(
    private val hotelRepo: HotelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AllReservationsUiState())
    val uiState: StateFlow<AllReservationsUiState> = _uiState

    fun load() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(loading = true)
        try {
            val all = hotelRepo.getAllReservations()
            val flatList = all.values.flatten()
            _uiState.value = AllReservationsUiState(loading = false, reservations = flatList)
        } catch (e: Exception) {
            _uiState.value = AllReservationsUiState(loading = false, reservations = emptyList(), message = e.localizedMessage ?: "Error")
        }
    }
}

data class AllReservationsUiState(
    val loading: Boolean = false,
    val reservations: List<Reservation> = emptyList(),
    val message: String? = null
)
