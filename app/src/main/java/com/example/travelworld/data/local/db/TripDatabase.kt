package com.example.travelworld.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.local.dao.UserAccessLogDao
import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.SubTripEntity
import com.example.travelworld.data.local.entity.TripEntity
import com.example.travelworld.data.local.entity.UserAccessLogEntity
import com.example.travelworld.data.local.entity.UserEntity


@Database(
    entities = [TripEntity::class, SubTripEntity::class, UserEntity::class, UserAccessLogEntity::class],
    version = 2, // Increment version
    exportSchema = false
)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun subTripDao(): SubTripDao
    abstract fun userDao(): UserDao
    abstract fun userAccessLogDao(): UserAccessLogDao
}