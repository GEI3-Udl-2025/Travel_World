package com.example.travelworld.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.travelworld.data.local.entity.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trips WHERE userLogin = :login")
    fun getTripsFlowForUser(login: String): Flow<List<TripEntity>>

    @Insert
    suspend fun addTrip(trip: TripEntity): Long

    @Query("DELETE FROM trips WHERE id = :tripId")
    suspend fun deleteTrip(tripId: Int)

    @Update
    suspend fun updateTrip(trip: TripEntity)

    @Query("DELETE FROM trips WHERE id = :tripId AND userLogin = :login")
    suspend fun deleteTripForUser(tripId: Int, login: String)

}