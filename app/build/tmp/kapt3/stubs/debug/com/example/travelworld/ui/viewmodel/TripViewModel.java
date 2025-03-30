package com.example.travelworld.ui.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.travelworld.domain.model.Trip;
import com.example.travelworld.domain.repository.TripRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u0016H\u0002J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001e"}, d2 = {"Lcom/example/travelworld/ui/viewmodel/TripViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/travelworld/domain/repository/TripRepository;", "(Lcom/example/travelworld/domain/repository/TripRepository;)V", "_trips", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/travelworld/domain/model/Trip;", "<set-?>", "lastSelectedTrip", "getLastSelectedTrip", "()Lcom/example/travelworld/domain/model/Trip;", "setLastSelectedTrip", "(Lcom/example/travelworld/domain/model/Trip;)V", "lastSelectedTrip$delegate", "Landroidx/compose/runtime/MutableState;", "trips", "Lkotlinx/coroutines/flow/StateFlow;", "getTrips", "()Lkotlinx/coroutines/flow/StateFlow;", "addTrip", "", "trip", "deleteTrip", "tripId", "", "loadTrips", "setSelectedTrip", "updateTrip", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TripViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.domain.repository.TripRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.travelworld.domain.model.Trip>> _trips = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.travelworld.domain.model.Trip>> trips = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState lastSelectedTrip$delegate = null;
    
    @javax.inject.Inject()
    public TripViewModel(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.repository.TripRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.travelworld.domain.model.Trip>> getTrips() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.travelworld.domain.model.Trip getLastSelectedTrip() {
        return null;
    }
    
    private final void setLastSelectedTrip(com.example.travelworld.domain.model.Trip p0) {
    }
    
    public final void setSelectedTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip) {
    }
    
    private final void loadTrips() {
    }
    
    public final void addTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip) {
    }
    
    public final void deleteTrip(int tripId) {
    }
    
    public final void updateTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip) {
    }
}