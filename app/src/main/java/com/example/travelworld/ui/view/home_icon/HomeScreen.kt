import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelworld.R
import com.example.travelworld.ui.view.home_icon.TripCard

@Preview
@Composable
//modicar más adelante esta pantalla para que sea favoritos o viajes favoritod o
// ddestinos favoritos o proximos viajes o anotaciones del un mismo viaje con detalles

fun HomeApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Quick Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(
                icon = Icons.Default.Add,
                label = stringResource(id = R.string.new_trip),
                onClick = { /* TODO: Navigate to trip creation */ },
                modifier = Modifier.weight(1f) // Ocupa la mitad del espacio
            )
            ActionButton(
                icon = Icons.Default.Search,
                label = stringResource(id = R.string.explore),
                onClick = { /* TODO: Navigate to explore */ },
                modifier = Modifier.weight(1f) // Ocupa la otra mitad del espacio
            )
        }

        // Recent Trips Section
        Text(
            text = stringResource(id = R.string.recent_trips),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) { index ->
                TripCard(
                    tripName = stringResource(id = R.string.trip) + " ${index + 1}",
                    destination = stringResource(id = R.string.destination ) + " ${index + 1}",
                    dates = "Jan 1 - Jan 7, 2024"
                )
            }
        }
    }
}

@Composable
fun ActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label)
        }
    }
}

