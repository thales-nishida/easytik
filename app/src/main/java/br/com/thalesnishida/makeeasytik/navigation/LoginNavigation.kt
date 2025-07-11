package br.com.thalesnishida.makeeasytik.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.thalesnishida.makeeasytik.extentions.navigateTo
import br.com.thalesnishida.makeeasytik.ui.screens.LoginScreen
import br.com.thalesnishida.makeeasytik.viewmodels.LoginViewModelImpl

fun NavGraphBuilder.loginScreen(navController: NavController) {
    composable<Login> {
        LoginScreen(navController, hiltViewModel<LoginViewModelImpl>())
    }
}

fun NavController.navigateToLoginScreen() {
    navigateTo(Login)
}