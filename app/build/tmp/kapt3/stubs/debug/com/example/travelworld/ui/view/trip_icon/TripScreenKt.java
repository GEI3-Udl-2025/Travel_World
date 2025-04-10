package com.example.travelworld.ui.view.trip_icon;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.example.travelworld.domain.model.Trip;
import com.example.travelworld.ui.viewmodel.TripViewModel;
import java.time.LocalDate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007\u001a:\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0003\u001a\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0010H\u0003\u00a8\u0006\u0014"}, d2 = {"TripApp", "", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/example/travelworld/ui/viewmodel/TripViewModel;", "TripItem", "trip", "Lcom/example/travelworld/domain/model/Trip;", "onEdit", "Lkotlin/Function0;", "onOpen", "onDelete", "isEndDateAfterStartDate", "", "startDateStr", "", "endDateStr", "isValidDate", "dateStr", "app_debug"})
public final class TripScreenKt {
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    @androidx.compose.runtime.Composable()
    public static final void TripApp(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.example.travelworld.ui.viewmodel.TripViewModel viewModel) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private static final boolean isValidDate(java.lang.String dateStr) {
        return false;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private static final boolean isEndDateAfterStartDate(java.lang.String startDateStr, java.lang.String endDateStr) {
        return false;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TripItem(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpen, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
}