package com.example.travelworld.data.repositoryImpl

import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.remote.mapper.toDomain
import com.example.travelworld.data.remote.mapper.toEntity
import com.example.travelworld.data.remote.api.HotelApiService
import com.example.travelworld.domain.model.Hotel
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.model.ReserveRequest
import com.example.travelworld.domain.repo.HotelRepository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelRepositoryImpl @Inject constructor(
    private val api: HotelApiService,
    private val tripDao: TripDao //ejemplo si necesito guardar en base de datos local despues de una operacion
) : HotelRepository {

    /* ---------- Hotels ---------- */
    override suspend fun getHotels(groupId: String): List<Hotel> =
        api.getHotels(groupId).map { it.toDomain() }

    /* ---------- Availability ---------- */
    override suspend fun getAvailability(
        groupId: String,
        start: String,
        end: String,
        hotelId: String?,
        city: String?
    ): List<Hotel> =
        api.getAvailability(groupId, start, end, hotelId, city)
            .available_hotels
            .map { it.toDomain() }

    /* ---------- Reserve & Cancel (within group) ---------- */
    override suspend fun reserve(groupId: String, request: ReserveRequest): Reservation =
        api.reserveRoom(groupId, request.toEntity()).reservation.toDomain()

    override suspend fun cancel(groupId: String, request: ReserveRequest): String =
        api.cancelReservation(groupId, request.toEntity()).message

    /* ---------- Reservations for a group ---------- */
    override suspend fun getGroupReservations(
        groupId: String,
        guestEmail: String?
    ): List<Reservation> =
        api.getGroupReservations(groupId, guestEmail).reservations.map { it.toDomain() }

    /* ---------- All reservations (admin) ---------- */
    override suspend fun getAllReservations(): Map<String, List<Reservation>> =
        api.getAllReservations()
            .groups
            .mapValues { entry -> entry.value.map { it.toDomain() } }

    /* ---------- Single-ID operations ---------- */
    override suspend fun getReservationById(resId: String): Reservation =
        api.getReservationById(resId).toDomain()

    override suspend fun cancelById(resId: String): Reservation =
        api.deleteReservationById(resId).toDomain()
}