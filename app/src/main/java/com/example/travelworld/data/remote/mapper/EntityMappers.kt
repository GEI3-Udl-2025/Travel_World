package com.example.travelworld.data.remote.mapper

import com.example.travelworld.data.remote.entity.HotelEntity
import com.example.travelworld.data.remote.entity.ReservationEntity
import com.example.travelworld.data.remote.entity.ReserveRequestEntity
import com.example.travelworld.data.remote.entity.RoomEntity
import com.example.travelworld.domain.model.Hotel
import com.example.travelworld.domain.model.Reservation
import com.example.travelworld.domain.model.ReserveRequest
import com.example.travelworld.domain.model.Room

fun HotelEntity.toDomain(): Hotel = Hotel(
    id        = id,
    name      = name,
    address   = address,
    rating    = rating,
    imageUrl  = image_url,
    rooms     = rooms
        ?.map { it.toDomain() }      // si no es null lo mapea
        ?: emptyList()               // si es null lista vacía
)

fun RoomEntity.toDomain(): Room = Room(
    id       = id,
    roomType = room_type,
    price    = price,
    images   = images ?: emptyList()
)

fun ReservationEntity.toDomain(): Reservation = Reservation(
    id         = id,
    hotelId    = hotel_id,
    roomId     = room_id,
    startDate  = start_date,
    endDate    = end_date,
    guestName  = guest_name,
    guestEmail = guest_email,
    hotel = hotel.toDomain(),   // HotelEntity → Hotel
    room  = room.toDomain()     // RoomEntity  → Room
)

fun ReserveRequest.toEntity(): ReserveRequestEntity = ReserveRequestEntity(
    hotel_id = hotelId,
    room_id = roomId,
    start_date = startDate,
    end_date = endDate,
    guest_name = guestName,
    guest_email = guestEmail
)