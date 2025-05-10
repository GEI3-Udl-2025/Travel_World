package com.example.travelworld.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.travelworld.domain.repo.TripRepositoryImpl
import com.example.travelworld.data.local.TripDatabase
import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.data.local.dao.UserDao
import com.example.travelworld.domain.repo.TripRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import firebase.com.protolitewrapper.BuildConfig
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}_preferences",
            Context.MODE_PRIVATE
        )


    @Provides
    @Singleton
    fun provideTripDatabase(@ApplicationContext context: Context): TripDatabase {
        return Room.databaseBuilder(
            context,
            TripDatabase::class.java,
            "my_database_name"
        ).build()
    }

    @Provides
    fun provideTripDao(db: TripDatabase): TripDao = db.tripDao()

    @Provides
    fun provideSubTripDao(db: TripDatabase): SubTripDao = db.subTripDao()

    @Provides
    @Singleton
    fun provideTripRepository(tripDao: TripDao, subTripDao: SubTripDao): TripRepository =
        TripRepositoryImpl(tripDao, subTripDao)

    @Provides
    fun provideUserDao(db: TripDatabase): UserDao = db.userDao()

}