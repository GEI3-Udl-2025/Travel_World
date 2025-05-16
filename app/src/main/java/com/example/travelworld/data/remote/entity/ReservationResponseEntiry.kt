package com.example.travelworld.data.remote.entity

/* ---------- reserva devuelta por POST /reserve ---------- */
data class ReservationResponseEntiry (
    val message: String,
    val nights: Int,
    val reservation: ReservationEntity        // ya trae hotel + room

)
