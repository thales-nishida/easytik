package br.com.thalesnishida.makeeasytik.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModelMock

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        OutlinedTextFieldDefault(
            value = uiState.nameTheme,
            onValueChange = { viewModel.updateNameTheme(it) },
            label = "Nome do Tema",
            isError = uiState.showNameThemeError
        )

        ButtonDefault(
            onClick = {
                viewModel.sendTheme(uiState.nameTheme)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Gerar")
        }

        Box(modifier = Modifier
            .weight(1f)
            .verticalScroll(rememberScrollState())) {
            Text(text = viewModel.uiState.value.textGenerated)
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
