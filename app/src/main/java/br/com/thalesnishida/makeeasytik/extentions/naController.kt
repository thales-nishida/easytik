package br.com.thalesnishida.makeeasytik.extentions

import androidx.navigation.NavController
import br.com.thalesnishida.makeeasytik.navigation.Route

fun NavController.navigateTo(route: Route) {
    val currentRoute = currentBackStackEntry?.destination?.route
    navigate(route) {
        currentRoute?.let {
            popUpTo(it) {
                inclusive = true
            }
        }
    }
}