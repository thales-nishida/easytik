package br.com.thalesnishida.makeeasytik.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.thalesnishida.makeeasytik.composables.ButtonDefault
import br.com.thalesnishida.makeeasytik.composables.OutlinedTextFieldDefault
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModel
import br.com.thalesnishida.makeeasytik.viewmodels.mock.HomeViewModelMock

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        OutlinedTextFieldDefault(
            value = uiState.nameTheme,
            onValueChange = { viewModel.updateNameTheme(it) },
            label = "Nome do Tema",
            isError = uiState.showNameThemeError
        )

        ButtonDefault(
            onClick = {
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Gerar")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel<HomeViewModelMock>()
    )
}
