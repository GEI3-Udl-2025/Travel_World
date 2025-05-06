package com.example.travelworld.data.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}