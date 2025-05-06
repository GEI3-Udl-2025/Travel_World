package com.example.travelworld.data.repo

import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.local.dao.UserAccessLogDao
import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.UserAccessLogEntity
import com.example.travelworld.data.local.entity.UserEntity
import com.example.travelworld.data.mapper.toDomain
import com.example.travelworld.data.mapper.toEntity
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val subTripDao: SubTripDao,
    private val userDao: UserDao,
    private val userAccessLogDao: UserAccessLogDao
) : TripRepository {

    // Listas mutables para almacenar datos en memoria
    private val trips = mutableListOf<Trip>()
    private val subTrips = mutableListOf<SubTrip>()

    override fun getTrips(userId: String): Flow<List<Trip>> {
        return tripDao.getTripsFlow().map { tripEntities ->
            tripEntities.map { tripEntity ->
                val subs = subTripDao.getSubTripsForTrip(tripEntity.id).map { it.toDomain() }
                tripEntity.toDomain(subs)
            }
        }
    }

    suspend fun createUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun getUser(userId: String): UserEntity? {
        return userDao.getUser(userId)
    }

    suspend fun isUsernameAvailable(username: String): Boolean {
        return userDao.getUserByUsername(username) == null
    }

    suspend fun logUserAccess(userId: String, action: String) {
        userAccessLogDao.logAccess(UserAccessLogEntity(userId = userId, action = action))
    }

    override suspend  fun addTrip(trip: Trip) {
        // Insertar la tarea en la DB
        tripDao.addTrip(trip.toEntity())
        // SubTrips se gestionan por separado si fuera necesario
    }

    override suspend  fun deleteTrip(tripId: Int) {
        tripDao.deleteTrip(tripId)
        // Por la ForeignKey con onDelete = CASCADE,
        // las subtareas también se borran automáticamente
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