package com.example.travelworld.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.travelworld.data.local.entity.SubTripEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\u0006\u0010\u000e\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/travelworld/data/local/dao/SubTripDao;", "", "addSubTrip", "", "subTrip", "Lcom/example/travelworld/data/local/entity/SubTripEntity;", "(Lcom/example/travelworld/data/local/entity/SubTripEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubTrip", "", "subTripId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSubTripsForTrip", "", "tripId", "updateSubTrip", "app_debug"})
@androidx.room.Dao()
public abstract interface SubTripDao {
    
    @androidx.room.Query(value = "SELECT * FROM subtrips WHERE parentTripId = :tripId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubTripsForTrip(int tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.travelworld.data.local.entity.SubTripEntity>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.entity.SubTripEntity subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM subtrips WHERE id = :subTripId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSubTrip(int subTripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.entity.SubTripEntity subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}