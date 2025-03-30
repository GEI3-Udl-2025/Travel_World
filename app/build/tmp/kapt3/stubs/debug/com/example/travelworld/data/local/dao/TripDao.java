package com.example.travelworld.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.travelworld.data.local.entity.TripEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\u0010H\'J\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0012"}, d2 = {"Lcom/example/travelworld/data/local/dao/TripDao;", "", "addTrip", "", "trip", "Lcom/example/travelworld/data/local/entity/TripEntity;", "(Lcom/example/travelworld/data/local/entity/TripEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTrip", "", "tripId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTrips", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTripsFlow", "Lkotlinx/coroutines/flow/Flow;", "updateTrip", "app_debug"})
@androidx.room.Dao()
public abstract interface TripDao {
    
    @androidx.room.Query(value = "SELECT * FROM trips")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTrips(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.travelworld.data.local.entity.TripEntity>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.entity.TripEntity trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM trips WHERE id = :tripId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTrip(int tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.entity.TripEntity trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM trips")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.travelworld.data.local.entity.TripEntity>> getTripsFlow();
}