package com.example.travelworld.domain.repo

import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.mapper.toDomain
import com.example.travelworld.data.mapper.toEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val subTripDao: SubTripDao
) : TripRepository {

    // Listas mutables para almacenar datos en memoria
    private val Trips = mutableListOf<Trip>()
    private val subTrips = mutableListOf<SubTrip>()

    override fun getTrips(): Flow<List<Trip>> {
        return tripDao.getTripsFlow().map { tripEntities ->
            tripEntities.map { tripEntity ->
                val subs = subTripDao.getSubTripsForTrip(tripEntity.id).map { it.toDomain() }
                tripEntity.toDomain(subs)
            }
        }
    }

    override suspend  fun addTrip(trip: Trip) {
        tripDao.addTrip(trip.toEntity())
    }

    override suspend  fun deleteTrip(tripId: Int) {
        tripDao.deleteTrip(tripId)
    }

    override suspend  fun updateTrip(trip: Trip) {
        tripDao.updateTrip(trip.toEntity())
    }

    override suspend  fun getSubTripsForTrip(tripId: Int): List<SubTrip> {
        return subTripDao.getSubTripsForTrip(tripId).map { it.toDomain() }
    }

    override suspend fun addSubTrip(subTrip: SubTrip) {
        subTripDao.addSubTrip(subTrip.toEntity())
    }

    override suspend fun deleteSubTrip(subTripId: Int) {
        subTripDao.deleteSubTrip(subTripId)
    }

    override suspend fun updateSubTrip(subTrip: SubTrip) {
        subTripDao.updateSubTrip(subTrip.toEntity())
    }
}