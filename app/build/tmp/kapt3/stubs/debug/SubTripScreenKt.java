
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.navigation.NavController;
import com.example.travelworld.domain.model.SubTrip;
import com.example.travelworld.ui.viewmodel.SubTripViewModel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a,\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0007\u00a8\u0006\u000e"}, d2 = {"SubTripApp", "", "navController", "Landroidx/navigation/NavController;", "tripId", "", "viewModel", "Lcom/example/travelworld/ui/viewmodel/SubTripViewModel;", "SubTripItem", "subTrip", "Lcom/example/travelworld/domain/model/SubTrip;", "onEdit", "Lkotlin/Function0;", "onDelete", "app_debug"})
public final class SubTripScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SubTripApp(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, int tripId, @org.jetbrains.annotations.NotNull()
    com.example.travelworld.ui.viewmodel.SubTripViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SubTripItem(@org.jetbrains.annotations.NotNull()
    com.example.travelworld.domain.model.SubTrip subTrip, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
}