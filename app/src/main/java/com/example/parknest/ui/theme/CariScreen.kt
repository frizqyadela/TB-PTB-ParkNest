package com.example.parknest.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF211C6A)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Search Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF211C6A))
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE6F5FF), MaterialTheme.shapes.small)
                        .padding(16.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(modifier = Modifier.fillMaxWidth()) {
                                if (searchQuery.isEmpty()) {
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

            // Map Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFB0C4DE))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map_placeholder),
                    contentDescription = "Map View",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Parking List
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                CariParkingSlotItem(
                    title = "Parkiran Gedung A",
                    status = "10 tersisa",
                    distance = "500m dari Anda",
                    actionText = "Lihat",
                    onClick = { /* Navigate to details */ }
                )
                Spacer(modifier = Modifier.height(8.dp))

                CariParkingSlotItem(
                    title = "Parkiran DPR",
                    status = "30 tersisa",
                    distance = "400m dari Anda",
                    actionText = "Lihat",
                    onClick = { /* Navigate to details */ }
                )
                Spacer(modifier = Modifier.height(8.dp))

                CariParkingSlotItem(
                    title = "Parkiran Gedung H",
                    status = "15 tersisa",
                    distance = "500m dari Anda",
                    actionText = "Lihat",
                    onClick = { /* Navigate to details */ }
                )
            }
        }
    }
}

@Composable
fun CariParkingSlotItem(
    title: String,
    status: String,
    distance: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9F9), MaterialTheme.shapes.small)
            .padding(16.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF211C6A)
            )
            Text(
                text = "Slot tersedia: $status",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Jarak: $distance",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Button(onClick = onClick) {
            Text(text = actionText)
        }
    }
}