package com.example.travelworld.data.remote.api


import com.example.travelworld.data.remote.entity.AvailabilityResponseEntity
import com.example.travelworld.data.remote.entity.HotelEntity
import com.example.travelworld.data.remote.entity.ReservationEntity
import com.example.travelworld.data.remote.entity.ReservationResponseEntiry
import com.example.travelworld.data.remote.entity.ReserveRequestEntity
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.DELETE

interface HotelApiService {

    /* ------------ Hotels & Availability ------------ */

    @GET("hotels/{group_id}/hotels")
    suspend fun getHotels(
        @Path("group_id") groupId: String
    ): List<HotelEntity>

    @GET("hotels/{group_id}/availability")
    suspend fun getAvailability(
        @Path("group_id") groupId: String,
        @Query("start_date") startDate: String,
        @Query("end_date")   endDate: String,
        @Query("hotel_id")   hotelId: String? = null,
        @Query("city")   city: String? = null
    ): AvailabilityResponseEntity

    /* ------------ Reservations by group ------------ */

    @POST("hotels/{group_id}/reserve")
    suspend fun reserveRoom(
        @Path("group_id") groupId: String,
        @Body             request: ReserveRequestEntity
    ): ReservationResponseEntiry

    @POST("hotels/{group_id}/cancel")
    suspend fun cancelReservation(
        @Path("group_id") groupId: String,
        @Body             request: CancelRequestEntity            // same fields as Reserve
    ): ApiMessageEntity                                           // e.g. { "message": "Reserva cancelada" }

    @GET("hotels/{group_id}/reservations")
    suspend fun getGroupReservations(
        @Path("group_id") groupId: String,
        @Query("guest_email") guestEmail: String? = null
    ): ReservationsWrapperEntity                                  // { reservations:[...] }

    /* ------------ Admin-level (all groups) ------------ */

    @GET("reservations")
    suspend fun getAllReservations(): AllReservationsEntity       // { groups:{ G01:[...], G02:[...] } }

    //TODO: filtrar reserva por grupo
    @GET("hotels/{group_id}/reservations")
    suspend fun getGroupReservations(
        @Path("group_id") groupId: String,
    ): ReservationsWrapperEntity

    @GET("reservations/{res_id}")
    suspend fun getReservationById(
        @Path("res_id") resId: String
    ): ReservationEntity

    @DELETE("reservations/{res_id}")
    suspend fun deleteReservationById(
        @Path("res_id") resId: String
    ): ReservationEntity                                          // returns the deleted object
}

/* Cancel uses the same body as Reserve */
typealias CancelRequestEntity = ReserveRequestEntity

/* Generic message wrapper */
data class ApiMessageEntity(val message: String)

/* Wrapper used by  GET /hotels/{group}/reservations */
data class ReservationsWrapperEntity(
    val reservations: List<ReservationEntity>
)

/* Wrapper used by  GET /reservations */
data class AllReservationsEntity(
    val groups: Map<String, List<ReservationEntity>>
)