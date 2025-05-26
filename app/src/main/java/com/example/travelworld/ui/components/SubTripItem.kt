package com.example.travelworld.ui.components

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelworld.domain.model.SubTrip

@Composable
fun SubTripItem(
    subTrip: SubTrip,
    onPhotoClick: (Uri) -> Unit,    // ← nuevo callback
    onEdit: () -> Unit,
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
                // 1️⃣ MINIATURA clicable
                subTrip.photoUri?.let { uriString ->
                    val uri = Uri.parse(uriString)
                    AsyncImage(
                        model = uri,
                        contentDescription = "Miniatura de ${subTrip.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable { onPhotoClick(uri) }
                            .padding(end = 12.dp)
                    )
                }

                // 2️⃣ Texto principal
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = subTrip.title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = subTrip.location,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "${subTrip.date}  ${subTrip.time}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    AnimatedVisibility(subTrip.isExpanded && subTrip.description.isNotBlank()) {
                        Text(
                            text = subTrip.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                // 3️⃣ Botones
                Row {
                    IconButton(onClick = onExpandClick, modifier = Modifier.size(40.dp)) {
                        Icon(
                            imageVector = if (subTrip.isExpanded) Icons.Filled.ArrowDropUp
                            else Icons.Filled.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = onEdit, modifier = Modifier.size(40.dp)) {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = onDelete, modifier = Modifier.size(40.dp)) {
                        Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}
