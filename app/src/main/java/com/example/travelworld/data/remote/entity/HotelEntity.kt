package com.example.travelworld.data.remote.entity

data class HotelEntity (
    val id: String,
    val name: String,
    val address: String,
    val rating: Int,
    val image_url: String,
    val rooms: List<RoomEntity>? = null
)