package com.example.travelworld.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.data.local.entity.UserEntity
import com.example.travelworld.domain.repository.TripRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    private val TAG = "AuthViewModel"
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState?>()
    val authState: MutableLiveData<AuthState?> = _authState


    init {
        checkAuthStatus()
    }


    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
        // [END send_email_verification]
    }

    private fun checkAuthStatus() {
        val user = auth.currentUser
        if (user != null) {
            user.reload().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.Unauthenticated
                    }
                } else {
                    _authState.value = AuthState.Unauthenticated
                }
            }
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }


    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.reload()?.addOnCompleteListener { reloadTask ->
                        if (reloadTask.isSuccessful) {
                            if (user.isEmailVerified) {
                                // Persist user access
                                viewModelScope.launch {
                                    repository.logUserAccess(user.uid, "LOGIN")
                                    // Check if user exists in local DB, if not create
                                    if (repository.getUser(user.uid) == null) {
                                        val newUser = UserEntity(
                                            id = user.uid,
                                            email = user.email ?: "",
                                            username = "", // Default empty, to be set in profile
                                            birthDate = java.util.Date(),
                                            address = "",
                                            country = "",
                                            phoneNumber = "",
                                            acceptEmails = false
                                        )
                                        repository.createUser(newUser)
                                    }
                                }
                                _authState.value = AuthState.Authenticated
                            } else {
                                _authState.value = AuthState.Error("Email address not verified. Please check your email before continuing.")
                                sendEmailVerification()
                                auth.signOut()
                            }
                        } else {
                            _authState.value = AuthState.Error("Failed to refresh user data")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }

    }


    fun signup(email : String,password : String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if (task.isSuccessful){
                    sendEmailVerification()
                    _authState.value = AuthState.Message("Please, confirm your email")
                    auth.signOut()
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signout() {
        auth.currentUser?.uid?.let { userId ->
            viewModelScope.launch {
                repository.logUserAccess(userId, "LOGOUT")
            }
        }
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun recoverPassword(email: String) {
        if (email.isEmpty()) {
            _authState.value = AuthState.Error("Email can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Message("Password reset email sent. Please check your inbox.")
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }
    fun resetAuthState() {
        _authState.value = null
    }

}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
    data class Message(val message: String) : AuthState()
}