package com.example.travelworld.domain.repo

import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getUser(login: String): Flow<UserEntity?> =
        userDao.getUser(login)

    suspend fun saveUser(user: UserEntity) {
        val exists = userDao.countByUsername(user.username, user.login)
        if (exists > 0) {
            throw IllegalArgumentException("El nombre de usuario ya está en uso")
        }
        // Si existe, update; si no, insert
        userDao.getUser(user.login).collect { existing ->
            if (existing == null) userDao.insert(user)
            else userDao.update(user)
        }
    }
}