package com.example.travelworld.ui.components

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelworld.domain.model.Trip


@Composable
fun TripItem(
    trip: Trip,
    onEdit: () -> Unit,
    onOpen: () -> Unit,
    onDelete: () -> Unit,
    onExpandClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 1️⃣ MINIATURA: justo al inicio del Row
                trip.photoUri?.let { uriString ->
                    AsyncImage(
                        model = Uri.parse(uriString),
                        contentDescription = "Miniatura de ${trip.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp)                       // tamaño de miniatura
                            .clip(RoundedCornerShape(4.dp))    // esquinas
                            .padding(end = 12.dp)
                    )
                }

                // 2️⃣ Texto principal
                // Columna con texto (izquierda)
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = trip.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "${trip.startDate} to ${trip.endDate}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    // Descripción (se expande debajo)
                    AnimatedVisibility (trip.isExpanded && trip.description.isNotBlank()) {
                        Text(
                            text = trip.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                // 3️⃣ Botones de acción
                // Contenedor de botones (derecha)
                Row {
                    IconButton(
                        onClick = onExpandClick,
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            imageVector = if (trip.isExpanded) Icons.Filled.ArrowDropUp
                            else Icons.Filled.ArrowDropDown,
                            contentDescription = if (trip.isExpanded) "Collapse"
                            else "Expand"
                        )
                    }

                    IconButton(
                        onClick = onEdit,
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit Trip",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = onOpen,
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Open Trip"
                        )
                    }

                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(35.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete Trip",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}