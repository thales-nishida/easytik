package br.com.thalesnishida.makeeasytik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Login) {
        homeScreen(navController)
        settingsScreen(navController)
        loginScreen(navController)
    }
}