package com.example.trivial

import EndScreen
import MyViewModel
import ScreenGame
import ScreenMenu
import ScreenSettings
import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trivial.navigation.Routes
import com.example.trivial.ui.theme.TrivialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrivialTheme {

                val myViewModel by viewModels<MyViewModel>()
                val navigation = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(
                        navController = navigation,
                        startDestination = Routes.PantallaSplash.route
                    ) {
                        composable(Routes.PantallaSplash.route) { SplashScreen(navigation) }
                        composable(Routes.PantallaMenu.route) { ScreenMenu(navigation, myViewModel) }
                        composable(Routes.PantallaJuego.route) { ScreenGame(navigation, myViewModel) }
                        composable(Routes.PantallaConfiguraciones.route) { ScreenSettings(navigation, myViewModel) }
                        composable(Routes.PantallaFinal.route) { EndScreen(navigation, myViewModel) }
                    }
                }
            }
        }
    }
}
