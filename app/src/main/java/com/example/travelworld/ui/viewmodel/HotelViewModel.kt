package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Hotel
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.model.ReserveRequest
import com.example.travelworld.domain.repo.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelRepo: HotelRepository
) : ViewModel() {

    // Reservar Hotel
    var hotels by mutableStateOf<List<Hotel>>(emptyList())
        private set
    var loadingHotels by mutableStateOf(false)
    var errorHotels by mutableStateOf<String?>(null)

    // Mis Reservas
    var myReservations by mutableStateOf<List<Reservation>>(emptyList())
        private set
    var loadingMyReservations by mutableStateOf(false)
    var errorMyReservations by mutableStateOf<String?>(null)

    // Todas las reservas (admin)
    var allReservations by mutableStateOf<List<Reservation>>(emptyList())
        private set
    var loadingAllReservations by mutableStateOf(false)
    var errorAllReservations by mutableStateOf<String?>(null)

    // --- Acciones ---

    // Buscar hoteles disponibles por ciudad y fechas
    fun searchHotels(groupId: String, city: String?, start: String, end: String) {
        viewModelScope.launch {
            loadingHotels = true
            errorHotels = null
            try {
                hotels = hotelRepo.getAvailability(groupId, start, end, null, city)
            } catch (e: Exception) {
                errorHotels = e.localizedMessage ?: "Error inesperado"
            }
            loadingHotels = false
        }
    }

    // Reservar habitación (puedes adaptar para feedback)
    fun reserveRoom(groupId: String, request: ReserveRequest, onResult: (Reservation?) -> Unit) {
        viewModelScope.launch {
            try {
                val res = hotelRepo.reserve(groupId, request)
                onResult(res)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    // Obtener mis reservas (por email)
    fun fetchMyReservations(groupId: String, email: String) {
        viewModelScope.launch {
            loadingMyReservations = true
            errorMyReservations = null
            try {
                myReservations = hotelRepo.getGroupReservations(groupId, guestEmail = email)
            } catch (e: Exception) {
                errorMyReservations = e.localizedMessage ?: "Error inesperado"
            }
            loadingMyReservations = false
        }
    }

    // Obtener todas las reservas (admin)
    fun fetchAllReservations() {
        viewModelScope.launch {
            loadingAllReservations = true
            errorAllReservations = null
            try {
                val all = hotelRepo.getAllReservations()
                // Unifica todas las reservas de todos los grupos en una lista
                allReservations = all.values.flatten()
            } catch (e: Exception) {
                errorAllReservations = e.localizedMessage ?: "Error inesperado"
            }
            loadingAllReservations = false
        }
    }
}
