package com.example.travelworld.di;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.room.Room;
import com.example.travelworld.data.SharedPrefsManager;
import com.example.travelworld.data.TripRepositoryImpl;
import com.example.travelworld.data.local.TripDatabase;
import com.example.travelworld.data.local.dao.SubTripDao;
import com.example.travelworld.data.local.dao.TripDao;
import com.example.travelworld.domain.repository.TripRepository;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import firebase.com.protolitewrapper.BuildConfig;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u0010\u001a\u00020\r2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000bH\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/example/travelworld/di/AppModule;", "", "()V", "provideSharedPreferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "provideSharedPrefsManager", "Lcom/example/travelworld/data/SharedPrefsManager;", "sharedPreferences", "provideSubTripDao", "Lcom/example/travelworld/data/local/dao/SubTripDao;", "db", "Lcom/example/travelworld/data/local/TripDatabase;", "provideTripDao", "Lcom/example/travelworld/data/local/dao/TripDao;", "provideTripDatabase", "provideTripRepository", "Lcom/example/travelworld/domain/repository/TripRepository;", "tripDao", "subTripDao", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.travelworld.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences provideSharedPreferences(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.travelworld.data.SharedPrefsManager provideSharedPrefsManager(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.travelworld.data.local.TripDatabase provideTripDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.travelworld.data.local.dao.TripDao provideTripDao(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.TripDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.example.travelworld.data.local.dao.SubTripDao provideSubTripDao(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.TripDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.travelworld.domain.repository.TripRepository provideTripRepository(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.dao.TripDao tripDao, @org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.dao.SubTripDao subTripDao) {
        return null;
    }
}