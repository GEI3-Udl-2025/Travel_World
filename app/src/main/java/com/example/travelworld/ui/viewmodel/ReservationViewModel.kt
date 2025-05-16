package com.example.travelworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.repo.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.travelworld.BuildConfig


@HiltViewModel
class ReservationsViewModel @Inject constructor(
    private val hotelRepo: HotelRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReservationsUiState())
    val uiState: StateFlow<ReservationsUiState> = _uiState
    val groupId = BuildConfig.GROUP_ID

    fun load(userEmail: String) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        try {
            val res = hotelRepo.getGroupReservations(groupId, guestEmail = userEmail)
            _uiState.value = ReservationsUiState(loading = false, reservations = res)
        } catch (e: Exception) {
            _uiState.update { it.copy(loading = false, message = e.localizedMessage ?: "Error cargando reservas") }
        }
    }

    fun cancelReservationById(resId: String, userEmail: String,) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        try {
            hotelRepo.cancelById(resId)
            val res = hotelRepo.getGroupReservations(groupId, guestEmail = userEmail)
            _uiState.value = ReservationsUiState(
                loading = false,
                reservations = res,
                message = "Reserva cancelada correctamente."
            )
        } catch (e: Exception) {
            _uiState.update { it.copy(loading = false, message = e.localizedMessage ?: "Error al cancelar") }
        }
    }
}

data class ReservationsUiState(
    val loading: Boolean = true,
    val reservations: List<Reservation> = emptyList(),
    val message: String? = null
)
