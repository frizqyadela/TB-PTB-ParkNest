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
import com.example.parknest.ui.theme.EditProfileScreen
import com.example.parknest.ui.theme.NotifikasiScreen
import com.example.parknest.ui.theme.RiwayatScreen
import com.example.parknest.ui.theme.ProfileScreen
import com.example.parknest.ui.theme.AccountSettingsScreen
import com.example.parknest.ui.theme.ChangePasswordScreen
import com.example.parknest.ui.theme.CurrentLocationScreen
import com.example.parknest.ui.theme.ParkingDetailScreen
import com.example.parknest.ui.theme.ParkingBookingScreen
import com.example.parknest.ui.theme.PaymentMethodScreen
import com.example.parknest.ui.theme.ParkingDetailPaymentScreen
import com.example.parknest.ui.theme.BookingDetailsScreen


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
                                CariScreen(navController = navController)
                            }
                            composable("notifikasi_screen") {
                                NotifikasiScreen(navController = navController)
                            }
                            composable("riwayat_screen") {
                                RiwayatScreen(navController = navController)
                            }
                            composable("profil_screen") {
                                ProfileScreen (navController = navController)
                            }
                            composable("editprofil_screen") {
                                EditProfileScreen(navController = navController)
                            }
                            composable("setting_screen") {
                                AccountSettingsScreen(navController = navController)
                            }
                            composable("ubahpassword_screen") {
                                ChangePasswordScreen(navController = navController)
                            }
                            composable("lokasisekarang_screen") {
                                CurrentLocationScreen(navController = navController)
                            }
                            composable("detailparkir_screen") {
                                ParkingDetailScreen(navController = navController)
                            }
                            composable("booking_screen") {
                                ParkingBookingScreen(navController = navController)
                            }
                            composable("metode_screen") {
                                PaymentMethodScreen(navController = navController)
                            }
                            composable("konfirmasi_screen") {
                                ParkingDetailPaymentScreen(navController = navController)
                            }
                            composable("detail_screen") {
                                BookingDetailsScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
