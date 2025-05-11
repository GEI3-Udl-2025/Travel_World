package com.example.travelworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.SubTripEntity
import com.example.travelworld.data.local.entity.TripEntity
import com.example.travelworld.data.local.entity.UserEntity


@Database(
    entities = [TripEntity::class, SubTripEntity::class, UserEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun subTripDao(): SubTripDao
    abstract fun userDao(): UserDao
}