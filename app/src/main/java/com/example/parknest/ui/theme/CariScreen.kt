package com.example.parknest.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavController
import com.example.parknest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CariScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cari Parkir",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF211C6A)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF211C6A)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Search Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFFE6F5FF),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box {
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        "Cari Parkiran",
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

            // Map Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF211C6A))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map_placeholder),
                    contentDescription = "Map View",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Parking Spots List
            ParkingSpotCard(
                name = "Parkiran Gedung A",
                availableSpots = "10 tersisa",
                distance = "500m dari Anda",
                imageRes = R.drawable.parkir_a,
                onClick = { navController.navigate("lokasisekarang_screen") }
            )

            ParkingSpotCard(
                name = "Parkiran DPR",
                availableSpots = "30 tersisa",
                distance = "400m dari Anda",
                imageRes = R.drawable.parkir_dpr,
                onClick = { /* Navigate to details */ }
            )

            ParkingSpotCard(
                name = "Parkiran Gedung H",
                availableSpots = "15 tersisa",
                distance = "500m dari Anda",
                imageRes = R.drawable.parkir_h,
                onClick = { /* Navigate to details */ }
            )
        }
    }
}

@Composable
fun ParkingSpotCard(
    name: String,
    availableSpots: String,
    distance: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF211C6A),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF211C6A)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color.Green, RoundedCornerShape(4.dp))
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Slot tersedia: $availableSpots",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "Jarak: $distance",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF211C6A)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(
                        "Lihat",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Parking Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}