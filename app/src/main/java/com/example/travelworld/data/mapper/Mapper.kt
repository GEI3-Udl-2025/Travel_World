package com.example.travelworld.data.mapper

import com.example.travelworld.data.local.entity.SubTripEntity
import com.example.travelworld.data.local.entity.TripEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip

//Trip
// De dominio a entidad, pasándole el login actual:
fun Trip.toEntity(userLogin: String): TripEntity =
    TripEntity(
        id          = id,
        title       = title,
        description = description,
        startDate   = startDate,
        endDate     = endDate,
        userLogin   = userLogin
    )

// De entidad a dominio
fun TripEntity.toDomain(subTrips: List<SubTrip>): Trip =
    Trip(
        id = id,
        title = title,
        description = description,
        startDate = startDate,
        endDate = endDate,
        subTrips = subTrips
    )

//SubTrip
// De dominio a entidad
fun SubTrip.toEntity(): SubTripEntity =
    SubTripEntity(
        id = id,
        parentTripId = parentTripId,
        title = title,
        date = date,
        time = time,
        location = location,
        description = description
    )

// De entidad a dominio
fun SubTripEntity.toDomain(): SubTrip =
    SubTrip(
        id = id,
        parentTripId = parentTripId,
        title = title,
        date = date,
        time = time,
        location = location,
        description = description
    )