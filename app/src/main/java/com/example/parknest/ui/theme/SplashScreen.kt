package com.example.parknest.ui.theme

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import com.example.parknest.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Gambar splash screen
    val image: Painter = painterResource(id = R.drawable.splash_image)

    // Menunggu beberapa detik sebelum melanjutkan ke halaman login
    LaunchedEffect(Unit) {
        // Delay untuk menampilkan splash screen selama 3 detik
        delay(3000)
        onTimeout()
    }

    // Menampilkan gambar splash screen di tengah layar
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Crop the image to fill the screen
        )
    }
}
