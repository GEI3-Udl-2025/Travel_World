package com.example.travelworld

import android.content.Context
import android.content.SharedPreferences
import com.example.travelworld.data.SharedPrefsManager
import com.example.travelworld.domain.repository.TripRepository
import com.example.travelworld.domain.repository.TripRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import firebase.com.protolitewrapper.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("${BuildConfig.APPLICATION_ID}_preferences", Context.MODE_PRIVATE)

    // context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) //bad implementation

    @Provides
    @Singleton
    fun provideSharedPrefsManager(
        sharedPreferences: SharedPreferences,
        @ApplicationContext context: Context
    ): SharedPrefsManager =
        SharedPrefsManager(sharedPreferences, context)


    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository = TripRepositoryImpl()

}