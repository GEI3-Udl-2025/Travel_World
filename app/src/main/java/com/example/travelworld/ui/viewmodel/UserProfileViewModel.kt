package com.example.travelworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.data.local.entity.UserEntity
import com.example.travelworld.domain.repository.TripRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.type.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: TripRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _userProfile = MutableStateFlow<UserEntity?>(null)
    val userProfile: StateFlow<UserEntity?> = _userProfile.asStateFlow()

    private val _usernameAvailable = MutableStateFlow(true)
    val usernameAvailable: StateFlow<Boolean> = _usernameAvailable.asStateFlow()

    fun loadUserProfile() {
        auth.currentUser?.uid?.let { userId ->
            viewModelScope.launch {
                _userProfile.value = repository.getUser(userId)
            }
        }
    }

    fun checkUsernameAvailability(username: String, currentUserId: String) {
        viewModelScope.launch {
            _usernameAvailable.value = repository.isUsernameAvailable(username, currentUserId) == null
        }
    }

    fun updateUserProfile(
        username: String,
        birthDate: Date,
        address: String,
        country: String,
        phoneNumber: String,
        acceptEmails: Boolean
    ) {
        auth.currentUser?.uid?.let { userId ->
            viewModelScope.launch {
                repository.updateUserProfile(
                    userId = userId,
                    username = username,
                    birthDate = birthDate,
                    address = address,
                    country = country,
                    phoneNumber = phoneNumber,
                    acceptEmails = acceptEmails
                )
                loadUserProfile() // Refresh after update
            }
        }
    }
}