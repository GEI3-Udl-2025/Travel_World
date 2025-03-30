package com.example.travelworld.domain.repository;

import com.example.travelworld.domain.model.SubTrip;
import com.example.travelworld.domain.model.Trip;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0010\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00120\u0014H&J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lcom/example/travelworld/domain/repository/TripRepository;", "", "addSubTrip", "", "subTrip", "Lcom/example/travelworld/domain/model/SubTrip;", "(Lcom/example/travelworld/domain/model/SubTrip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTrip", "trip", "Lcom/example/travelworld/domain/model/Trip;", "(Lcom/example/travelworld/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubTrip", "subTripId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTrip", "tripId", "getSubTripsForTrip", "", "getTrips", "Lkotlinx/coroutines/flow/Flow;", "updateSubTrip", "updateTrip", "app_debug"})
public abstract interface TripRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.travelworld.domain.model.Trip>> getTrips();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTrip(int tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSubTripsForTrip(int tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.travelworld.domain.model.SubTrip>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteSubTrip(int subTripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}