package com.example.travelworld.data;

import com.example.travelworld.data.local.dao.SubTripDao;
import com.example.travelworld.data.local.dao.TripDao;
import com.example.travelworld.domain.model.SubTrip;
import com.example.travelworld.domain.model.Trip;
import com.example.travelworld.domain.repository.TripRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\u0015\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00170\u0019H\u0016J\u0016\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/travelworld/data/TripRepositoryImpl;", "Lcom/example/travelworld/domain/repository/TripRepository;", "tripDao", "Lcom/example/travelworld/data/local/dao/TripDao;", "subTripDao", "Lcom/example/travelworld/data/local/dao/SubTripDao;", "(Lcom/example/travelworld/data/local/dao/TripDao;Lcom/example/travelworld/data/local/dao/SubTripDao;)V", "addSubTrip", "", "subTrip", "Lcom/example/travelworld/domain/model/SubTrip;", "(Lcom/example/travelworld/domain/model/SubTrip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTrip", "trip", "Lcom/example/travelworld/domain/model/Trip;", "(Lcom/example/travelworld/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSubTrip", "subTripId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTrip", "tripId", "getSubTripsForTrip", "", "getTrips", "Lkotlinx/coroutines/flow/Flow;", "updateSubTrip", "updateTrip", "app_debug"})
public final class TripRepositoryImpl implements com.example.travelworld.domain.repository.TripRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.data.local.dao.TripDao tripDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.data.local.dao.SubTripDao subTripDao = null;
    
    @javax.inject.Inject()
    public TripRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.dao.TripDao tripDao, @org.jetbrains.annotations.NotNull()
    com.example.travelworld.data.local.dao.SubTripDao subTripDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.travelworld.domain.model.Trip>> getTrips() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteTrip(int tripId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSubTripsForTrip(int tripId, @org.jetbrains.annotations.NotNull()
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