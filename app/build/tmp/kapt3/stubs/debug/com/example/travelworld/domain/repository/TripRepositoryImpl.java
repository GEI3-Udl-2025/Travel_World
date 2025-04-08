package com.example.travelworld.domain.repository;

import com.example.travelworld.domain.model.SubTrip;
import com.example.travelworld.domain.model.Trip;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005H\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00162\u0006\u0010\u0014\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016H\u0016J\u0016\u0010\u0018\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0019\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005H\u0096@\u00a2\u0006\u0002\u0010\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/travelworld/domain/repository/TripRepositoryImpl;", "Lcom/example/travelworld/domain/repository/TripRepository;", "()V", "Trips", "", "Lcom/example/travelworld/domain/model/Trip;", "subTrips", "Lcom/example/travelworld/domain/model/SubTrip;", "addSubTrip", "", "subTrip", "(Lcom/example/travelworld/domain/model/SubTrip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTrip", "Trip", "(Lcom/example/travelworld/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubTrip", "subTripId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTrip", "TripId", "getSubTripsForTrip", "", "getTrips", "updateSubTrip", "updateTrip", "app_debug"})
public final class TripRepositoryImpl implements com.example.travelworld.domain.repository.TripRepository {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.travelworld.domain.model.Trip> Trips = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.travelworld.domain.model.SubTrip> subTrips = null;
    
    @javax.inject.Inject()
    public TripRepositoryImpl() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.example.travelworld.domain.model.Trip> getTrips() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip Trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteTrip(int TripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip Trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSubTripsForTrip(int TripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.travelworld.domain.model.SubTrip>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteSubTrip(int subTripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}