package br.com.thalesnishida.makeeasytik.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.thalesnishida.makeeasytik.extentions.navigateTo
import br.com.thalesnishida.makeeasytik.ui.screens.SettingsScreen
import br.com.thalesnishida.makeeasytik.viewmodels.SettingsViewModelImpl

fun NavGraphBuilder.settingsScreen(navController: NavController) {
    composable<Settings> {
        SettingsScreen(viewModel = hiltViewModel<SettingsViewModelImpl>())
    }
}

fun NavController.navigateToSettingsScreen() {
    navigateTo(Settings)
}