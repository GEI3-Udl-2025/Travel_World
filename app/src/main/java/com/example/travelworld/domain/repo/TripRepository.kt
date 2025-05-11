package com.example.travelworld.domain.repo

import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz unificada para manejar Trips y SubTrips.
 * La implementación (TripRepositoryImpl) obtiene internamente
 * el login del usuario autenticado.
 */
interface TripRepository {
    // ——— Trips ———
    /** Flujo de viajes del usuario actual. */
    fun getTrips(): Flow<List<Trip>>

    /** Añade un nuevo viaje para el usuario actual. */
    suspend fun addTrip(trip: Trip)

    /** Actualiza un viaje existente del usuario actual. */
    suspend fun updateTrip(trip: Trip)

    /** Elimina un viaje (solo si pertenece al usuario actual). */
    suspend fun deleteTrip(tripId: Int)

    // ——— SubTrips ———
    suspend fun getSubTripsForTrip(tripId: Int): List<SubTrip>
    suspend fun addSubTrip(subTrip: SubTrip)
    suspend fun deleteSubTrip(subTripId: Int)
    suspend fun updateSubTrip(subTrip: SubTrip)
}
