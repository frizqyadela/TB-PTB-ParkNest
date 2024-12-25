package com.example.parknest.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.parknest.R

data class ParkingHistory(
    val location: String,
    val date: String,
    val imageResId: Int,
    val entryTime: String = "08:00",
    val exitTime: String = "10:00",
    val duration: String = "2 jam",
    val fee: String = "Rp 5.000"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiwayatScreen(navController: NavController) {
    var selectedHistory by remember { mutableStateOf<ParkingHistory?>(null) }

    val parkingHistories = listOf(
        ParkingHistory("Parkiran Gedung A", "5 Oktober 2024", R.drawable.parkir_a),
        ParkingHistory("Parkiran DPR", "29 September 2024", R.drawable.parkir_dpr),
        ParkingHistory("Parkiran Gedung H", "28 September 2024", R.drawable.parkir_h)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(parkingHistories) { history ->
                RiwayatItem(
                    history = history,
                    onDetailClick = { selectedHistory = history }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Detail Dialog
        if (selectedHistory != null) {
            DetailDialog(
                history = selectedHistory!!,
                onDismiss = { selectedHistory = null }
            )
        }
    }
}

@Composable
fun RiwayatItem(
    history: ParkingHistory,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onDetailClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF211C6A),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = history.location,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF211C6A)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = "Tanggal Parkir",
                        fontSize = 14.sp,
                        color = Color(0xFF211C6A)
                    )
                    Text(
                        text = ": ${history.date}",
                        fontSize = 14.sp,
                        color = Color(0xFF211C6A)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onDetailClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF211C6A))
                ) {
                    Text("Detail")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = history.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun DetailDialog(
    history: ParkingHistory,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Detail Parkir",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF211C6A)
                )
                Spacer(modifier = Modifier.height(16.dp))

                DetailRow("Lokasi", history.location)
                DetailRow("Tanggal", history.date)
                DetailRow("Jam Masuk", history.entryTime)
                DetailRow("Jam Keluar", history.exitTime)
                DetailRow("Durasi", history.duration)
                DetailRow("Biaya", history.fee)

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF211C6A))
                ) {
                    Text("Tutup")
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF211C6A),
            fontWeight = FontWeight.Medium
        )
    }
}