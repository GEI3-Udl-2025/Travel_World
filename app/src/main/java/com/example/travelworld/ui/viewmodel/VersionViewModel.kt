package com.example.travelworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.travelworld.data.AppInfo
import javax.inject.Inject

@HiltViewModel
class VersionViewModel @Inject constructor() : ViewModel() {
    val versionName: String = AppInfo.versionName
    val versionCode: Int = AppInfo.versionCode
}