package com.example.travelworld.data

import android.content.Context
import android.content.SharedPreferences
import com.example.travelworld.utils.LanguageChangeUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsManager @Inject constructor(
    private val preferences: SharedPreferences,
    @ApplicationContext private val context: Context
) {
    private val languageChangeUtil = LanguageChangeUtil()

    var userLanguage: String?
        get() = preferences.getString("user_language", "en")
        set(value) {
            value?.let { lang ->
                preferences.edit().putString("user_language", lang).apply()
                languageChangeUtil.changeLanguage(context, lang)
            }
        }

    var darkTheme: Boolean
        get() = preferences.getBoolean("dark_theme", false)
        set(value) = preferences.edit().putBoolean("dark_theme", value).apply()
}
