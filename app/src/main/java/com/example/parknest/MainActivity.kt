package com.example.parknest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parknest.ui.theme.ParkNestTheme
import com.example.parknest.ui.theme.LoginScreen
import com.example.parknest.ui.theme.RegisterScreen
import com.example.parknest.ui.theme.SplashScreen
import com.example.parknest.ui.theme.DashboardScreen
import com.example.parknest.ui.theme.CariScreen
import com.example.parknest.ui.theme.NotifikasiScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ParkNestTheme {
                // Create NavController for navigation
                val navController = rememberNavController()

                // Scaffold for overall UI structure
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        // NavHost to control navigation between screens
                        NavHost(navController = navController, startDestination = "splash_screen") {
                            composable("splash_screen") {
                                SplashScreen {
                                    // After splash screen, navigate to login screen
                                    navController.navigate("login_screen") {
                                        // To avoid re-navigation to splash screen if user navigates back
                                        popUpTo("splash_screen") { inclusive = true }
                                    }
                                }
                            }
                            composable("login_screen") {
                                LoginScreen(navController = navController, modifier = Modifier.padding(innerPadding))
                            }
                            composable("register_screen") {
                                RegisterScreen(navController = navController)
                            }
                            composable("dashboard_screen") {
                                DashboardScreen(navController = navController)
                            }
                            composable("cari_screen") {
                                DashboardScreen(navController = navController)
                            }
                            composable("cari_screen") {
                                CariScreen(navController = navController)
                            }
                            composable("notifikasi_screen") {
                                NotifikasiScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
