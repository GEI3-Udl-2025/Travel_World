package com.example.travelworld.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.data.local.entity.UserEntity
import com.example.travelworld.domain.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


import com.google.firebase.auth.FirebaseAuth

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // ① Recuperamos el email del usuario o lanzamos si no existe
    private val login: String = FirebaseAuth
        .getInstance()
        .currentUser
        ?.email
        ?: throw IllegalStateException("Usuario no autenticado")

    // ② Ahora ya podemos declarar _user sin problema
    private val _user = MutableLiveData<UserEntity?>()
    val user: LiveData<UserEntity?> = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        viewModelScope.launch {
            repo.getUser(login).collect { _user.postValue(it) }
        }
    }

    fun save(
        username: String,
        birthdate: Date,
        address: String,
        country: String,
        phone: String,
        acceptEmails: Boolean
    ) {
        viewModelScope.launch {
            try {
                val u = UserEntity(login, username, birthdate, address, country, phone, acceptEmails)
                repo.saveUser(u)
                _error.postValue(null)
            } catch (e: IllegalArgumentException) {
                _error.postValue(e.message)
            }
        }
    }
}
