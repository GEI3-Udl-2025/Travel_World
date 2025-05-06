package com.example.travelworld.data.repo


import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun getUserByLogin(login: String): UserEntity? {
        return userDao.getUserByLogin(login)
    }

    suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }
}