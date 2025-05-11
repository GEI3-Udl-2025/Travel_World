package com.example.travelworld.domain.repo

import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.mapper.toDomain
import com.example.travelworld.data.mapper.toEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val subTripDao: SubTripDao
) : TripRepository {

    // Esto ya existía en tu código:
    private val currentLogin: String
        get() = FirebaseAuth.getInstance().currentUser
            ?.email
            ?: throw IllegalStateException("Usuario no autenticado")

    override fun getTrips(): Flow<List<Trip>> =
        tripDao.getTripsFlowForUser(currentLogin)
            .map { entities ->
                entities.map { e ->
                    val subs = subTripDao.getSubTripsForTrip(e.id).map { it.toDomain() }
                    e.toDomain(subs)
                }
            }

    override suspend fun addTrip(trip: Trip) {
        tripDao.addTrip(trip.toEntity(currentLogin))
    }

    override suspend fun updateTrip(trip: Trip) {
        tripDao.updateTrip(trip.toEntity(currentLogin))
    }

    override suspend fun deleteTrip(tripId: Int) {
        tripDao.deleteTripForUser(tripId, currentLogin)
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