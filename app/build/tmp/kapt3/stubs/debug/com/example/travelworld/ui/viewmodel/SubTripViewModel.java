package com.example.travelworld.ui.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.example.travelworld.domain.model.SubTrip;
import com.example.travelworld.domain.repository.TripRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000fJ\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\tR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001a"}, d2 = {"Lcom/example/travelworld/ui/viewmodel/SubTripViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/travelworld/domain/repository/TripRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/example/travelworld/domain/repository/TripRepository;Landroidx/lifecycle/SavedStateHandle;)V", "_subTrips", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lcom/example/travelworld/domain/model/SubTrip;", "subTrips", "", "getSubTrips", "()Ljava/util/List;", "tripId", "", "getTripId", "()I", "addSubTrip", "", "subTrip", "deleteSubTrip", "subTripId", "loadSubTrips", "updateSubTrip", "updatedSubTrip", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SubTripViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.travelworld.domain.repository.TripRepository repository = null;
    private final int tripId = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.snapshots.SnapshotStateList<com.example.travelworld.domain.model.SubTrip> _subTrips = null;
    
    @javax.inject.Inject()
    public SubTripViewModel(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.repository.TripRepository repository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    public final int getTripId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.travelworld.domain.model.SubTrip> getSubTrips() {
        return null;
    }
    
    private final void loadSubTrips() {
    }
    
    public final void addSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip) {
    }
    
    public final void deleteSubTrip(int subTripId) {
    }
    
    public final void updateSubTrip(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip updatedSubTrip) {
    }
}