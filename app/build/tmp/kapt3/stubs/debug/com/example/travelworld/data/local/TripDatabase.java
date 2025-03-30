package com.example.travelworld.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.travelworld.data.local.dao.SubTripDao;
import com.example.travelworld.data.local.dao.TripDao;
import com.example.travelworld.data.local.entity.SubTripEntity;
import com.example.travelworld.data.local.entity.TripEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/example/travelworld/data/local/TripDatabase;", "Landroidx/room/RoomDatabase;", "()V", "subTripDao", "Lcom/example/travelworld/data/local/dao/SubTripDao;", "tripDao", "Lcom/example/travelworld/data/local/dao/TripDao;", "app_debug"})
@androidx.room.Database(entities = {com.example.travelworld.data.local.entity.TripEntity.class, com.example.travelworld.data.local.entity.SubTripEntity.class}, version = 1, exportSchema = false)
public abstract class TripDatabase extends androidx.room.RoomDatabase {
    
    public TripDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.travelworld.data.local.dao.TripDao tripDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.travelworld.data.local.dao.SubTripDao subTripDao();
}