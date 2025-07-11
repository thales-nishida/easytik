package br.com.thalesnishida.makeeasytik.navigation

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
abstract class Route {
    fun navigate(navController: NavController) {
        when(this) {
            Home -> navController.navigateToHomeScreen()
            Settings -> navController.navigateToSettingsScreen()
            Login -> navController.navigateToLoginScreen()
            else -> None
        }
    }
}

@Serializable
data object None: Route()

@Serializable
data object Home: Route()

@Serializable
data object Settings: Route()

@Serializable
data object Login: Route()
