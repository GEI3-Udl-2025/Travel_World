package com.example.travelworld.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.travelworld.domain.model.Trip;
import com.example.travelworld.domain.repository.TripRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\rH\u0002J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/example/travelworld/ui/viewmodel/TripViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/travelworld/domain/repository/TripRepository;", "(Lcom/example/travelworld/domain/repository/TripRepository;)V", "_trips", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/example/travelworld/domain/model/Trip;", "trips", "", "getTrips", "()Ljava/util/List;", "addTrip", "", "Trip", "deleteTrip", "TripId", "", "loadTrips", "updateTrip", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TripViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.domain.repository.TripRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.example.travelworld.domain.model.Trip> _trips = null;
    
    @javax.inject.Inject()
    public TripViewModel(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.repository.TripRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.travelworld.domain.model.Trip> getTrips() {
        return null;
    }
    
    private final void loadTrips() {
    }
    
    public final void addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip Trip) {
    }
    
    public final void deleteTrip(int TripId) {
    }
    
    public final void updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip Trip) {
    }
}