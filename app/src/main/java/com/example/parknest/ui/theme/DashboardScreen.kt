package com.example.parknest.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.clickable
import com.example.parknest.R

@Composable
fun ParkingSlotItem(title: String, status: String, distance: String, actionText: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, MaterialTheme.shapes.small)
            .padding(16.dp)
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick), // Make it clickable
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF211C6A))
            Text(text = "Slot tersedia: $status", fontSize = 14.sp, color = Color.Gray)
            Text(text = "Jarak: $distance", fontSize = 14.sp, color = Color.Gray)
        }
        Button(onClick = onClick) {
            Text(text = actionText)
        }
    }
}

@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { innerPadding ->
            // Membuat konten dapat discroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())  // Menambahkan scroll
            ) {
                // Header Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFFFFF))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Hai, Kamu!",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF211C6A)
                            )
                            Text(
                                text = "Siap parkir dengan mudah",
                                fontSize = 16.sp,
                                color = Color(0xFF211C6A)
                            )
                            Text(
                                text = "di UNAND? 🚗",
                                fontSize = 16.sp,
                                color = Color(0xFF211C6A)
                            )
                        }
                        Row {
                            IconButton(onClick = {navController.navigate("notifikasi_screen") }) {
                                Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.Gray)
                            }
                            IconButton(onClick = { /* Handle profile */ }) {
                                Icon(Icons.Filled.AccountCircle, contentDescription = "Profile", tint = Color.Gray)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))

                    Image(
                        painter = painterResource(id = R.drawable.parkir), // Pastikan gambar parkir.png ada di folder res/drawable
                        contentDescription = "Parkir Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Sesuaikan ukuran gambar sesuai kebutuhan
                            .padding(bottom = 8.dp)
                    )

                    // Search Bar
                    BasicTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE6F5FF), MaterialTheme.shapes.small)
                            .padding(16.dp),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    if ("".isEmpty()) {
                                        Text(
                                            text = "Cari Parkiran",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                }

                // Real-Time Slot Availability Section
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF211C6A))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Real-Time Slot Availability",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEFF396)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Parking List Example
                    ParkingSlotItem(
                        title = "Parkiran Gedung A",
                        status = "10 tersisa",
                        distance = "500m dari Anda",
                        actionText = "Pesan Sekarang!" ,
                        onClick = { /* Handle Pesan Sekarang action */ }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ParkingSlotItem(
                        title = "Parkiran Gedung B",
                        status = "Penuh",
                        distance = "300m dari Anda",
                        actionText = "Lihat Lokasi Lain",
                        onClick = { /* Handle Lihat Lokasi Lain action */ }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Clickable "Lihat lainnya" text aligned to the right
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Lihat lainnya",
                            fontSize = 14.sp,
                            color = Color(0xFFEFF396),
                            modifier = Modifier.clickable(onClick = { /* Handle click action */ })
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick Actions
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        QuickActionItem("Riwayat Parkir") {
                            /* Handle Riwayat Parkir click */
                        }
                        QuickActionItem("Tiket Aktif") {
                            /* Handle Tiket Aktif click */
                        }
                        QuickActionItem("Waktu Parkir") {
                            /* Handle Waktu Parkir click */
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun QuickActionItem(text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick) // Make it clickable
    ) {
        Icon(Icons.Filled.CarRental, contentDescription = text, tint = Color(0xFFEFF396))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontSize = 12.sp, color = Color.White)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { navController.navigate("dashboard_screen") }  // Handle Home navigation
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Cari Parkir") },
            label = { Text("Cari Parkir") },
            selected = false,
            onClick = { navController.navigate("cari_screen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AddCircle, contentDescription = "Pesan Slot", tint = Color(0xFFFFD700)) },
            label = { Text("Pesan Slot") },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.History, contentDescription = "Riwayat") },
            label = { Text("Riwayat") },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profil") },
            label = { Text("Profil") },
            selected = false,
            onClick = { /* Handle navigation */ }
        )
    }
}
