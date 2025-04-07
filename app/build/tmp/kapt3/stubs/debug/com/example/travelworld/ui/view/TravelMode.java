package com.example.travelworld.ui.view;

import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.navigation.NavController;
import com.example.travelworld.R;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/travelworld/ui/view/TravelMode;", "", "(Ljava/lang/String;I)V", "HOME", "TRIP", "ITINERARY", "USER_PREFERENCE", "app_debug"})
public enum TravelMode {
    /*public static final*/ HOME /* = new HOME() */,
    /*public static final*/ TRIP /* = new TRIP() */,
    /*public static final*/ ITINERARY /* = new ITINERARY() */,
    /*public static final*/ USER_PREFERENCE /* = new USER_PREFERENCE() */;
    
    TravelMode() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.example.travelworld.ui.view.TravelMode> getEntries() {
        return null;
    }
}