package br.com.thalesnishida.makeeasytik.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.thalesnishida.makeeasytik.extentions.navigateTo
import br.com.thalesnishida.makeeasytik.ui.screens.HomeScreen
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModelImpl


fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable<Home> {
        HomeScreen(navController, hiltViewModel<HomeViewModelImpl>())
    }
}

fun NavController.navigateToHomeScreen() {
    navigateTo(Home)
}