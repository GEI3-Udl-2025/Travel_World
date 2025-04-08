package com.example.travelworld.domain.repository

import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor() : TripRepository {

    // Listas mutables para almacenar datos en memoria
    private val Trips = mutableListOf<Trip>()
    private val subTrips = mutableListOf<SubTrip>()

    override fun getTrips(): List<Trip> {
        // Asignamos a cada Trip las subtareas correspondientes
        return Trips.map { Trip ->
            Trip.copy(subTrips = subTrips.filter { it.parentTripId == Trip.id })
        }
    }

    override suspend fun addTrip(Trip: Trip) {
        // Generamos un id simple basado en el tamaño actual
        val newTrip = Trip.copy(id = Trips.size + 1)
        Trips.add(newTrip)
    }

    override suspend fun deleteTrip(TripId: Int) {
        Trips.removeAll { it.id == TripId }
        // También eliminamos sus subtareas
        subTrips.removeAll { it.parentTripId == TripId }
    }

    override suspend fun updateTrip(Trip: Trip) {
        // Buscar la tarea por su id y actualizarla
        val index = Trips.indexOfFirst { it.id == Trip.id }
        if (index != -1) {
            Trips[index] = Trip
        }
    }

    override suspend fun getSubTripsForTrip(TripId: Int): List<SubTrip> {
        return subTrips.filter { it.parentTripId == TripId }
    }

    override suspend fun addSubTrip(subTrip: SubTrip) {
        // Generamos un id simple para la subTrip
        val newSubTrip = subTrip.copy(id = subTrips.size + 1)
        subTrips.add(newSubTrip)
    }

    override suspend fun deleteSubTrip(subTripId: Int) {
        subTrips.removeAll { it.id == subTripId }
    }

    override suspend fun updateSubTrip(subTrip: SubTrip) {
        // Buscamos la posición de la subtarea a actualizar
        val index = subTrips.indexOfFirst { it.id == subTrip.id }
        if (index != -1) {
            subTrips[index] = subTrip
        }
    }
}