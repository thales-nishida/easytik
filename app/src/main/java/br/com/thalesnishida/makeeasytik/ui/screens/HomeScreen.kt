package br.com.thalesnishida.makeeasytik.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModel
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        Text("HELLO ${uiState.appName}")


        Button(onClick = { viewModel.setTest("TEST") }) {
            Text("Test Cache")
        }
    }
}