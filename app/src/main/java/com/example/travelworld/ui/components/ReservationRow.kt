package com.example.travelworld.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelworld.BuildConfig
import com.example.travelworld.domain.model.Reservation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter

@Composable
fun ReservationRow(
    reserva: Reservation,
    modifier: Modifier = Modifier,
    onDelete: (() -> Unit)? = null
) {
    var showHotelDialog by remember { mutableStateOf(false) }
    var showRoomDialog by remember { mutableStateOf(false) }

    val base = BuildConfig.HOTELS_API_URL.trimEnd('/')
    val hotelImg = base + (reserva.hotel.imageUrl ?: "")
    val roomImgs = reserva.room.images?.map { base + it } ?: emptyList()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            // Imagen hotel, clicable para ampliar
            AsyncImage(
                model = hotelImg,
                contentDescription = "Imagen del hotel",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .padding(end = 12.dp)
                    .clickable { showHotelDialog = true }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Hotel,
                        contentDescription = "Hotel",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        reserva.hotel.name,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Usuario",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        reserva.guestEmail,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Fecha entrada",
                        tint = Color(0xFF4CAF50), // Verde
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("Check-in: ${reserva.startDate}")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Fecha salida",
                        tint = Color(0xFFF44336), // Rojo
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("Check-out: ${reserva.endDate}")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Hotel,
                        contentDescription = "Habitación",
                        tint = Color(0xFF2196F3), // Azul
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text("Habitación: ${reserva.room.roomType}")

                    // Botón lupa para galería habitación
                    if (roomImgs.isNotEmpty()) {
                        IconButton(
                            onClick = { showRoomDialog = true },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoLibrary,
                                contentDescription = "Ver foto habitación",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }


                }
            }
            if (onDelete != null) {
                IconButton(
                    onClick = { onDelete() },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Cancelar reserva",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }


        // Dialogo hotel
        if (showHotelDialog) {
            AlertDialog(
                onDismissRequest = { showHotelDialog = false },
                confirmButton = {},
                text = {
                    AsyncImage(
                        model = hotelImg,
                        contentDescription = "Hotel grande",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            )
        }
        // Diálogo galería habitación
        if (showRoomDialog) {
            AlertDialog(
                onDismissRequest = { showRoomDialog = false },
                confirmButton = {},
                text = {
                    // Muestra la galería, o la primera imagen si quieres
                    // O usa tu RoomImageCarousel si quieres carrusel:
                    RoomImageCarousel(
                        images = roomImgs,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun RoomImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    if (images.isEmpty()) return                       // nada que mostrar

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    Column(modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.6f)                    // 16:10 aprox
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        /* mini indicador de páginas */
        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            repeat(images.size) { index ->
                val selected = pagerState.currentPage == index
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = if (selected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    modifier = Modifier
                        .size(8.dp)
                        .padding(horizontal = 2.dp)
                ) {}
            }
        }
    }
}


