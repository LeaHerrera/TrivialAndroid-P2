package com.example.trivial.navigation

sealed class Routes(val route: String) {
    object PantallaSplash: Routes("splash_screen")
    object PantallaMenu: Routes("menu_screen")
    object PantallaJuego: Routes("game_screen")
    object PantallaFinal: Routes("end_screen")
    object PantallaConfiguraciones: Routes("sttings_screen")

}
