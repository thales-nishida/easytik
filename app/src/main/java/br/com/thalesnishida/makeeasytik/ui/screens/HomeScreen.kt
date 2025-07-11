package br.com.thalesnishida.makeeasytik.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.thalesnishida.makeeasytik.composables.AudioPlay
import br.com.thalesnishida.makeeasytik.composables.ButtonDefault
import br.com.thalesnishida.makeeasytik.composables.OutlinedTextFieldDefault
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModel
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModelMock
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.onEach { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }.launchIn(this)
    }
    LaunchedEffect(uiState.listAudios) {
        viewModel.listAudio()
    }

    Column(
        modifier = Modifier.padding(14.dp)
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

        ButtonDefault(
            onClick = {
                viewModel.createAudio()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.enableButton
        ) {
            Text("Create Audio")
        }

        ButtonDefault(
            onClick = {
                viewModel.listAudio()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("List Audios")
        }

        val files: List<File> = uiState.listAudios

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(files) { file ->
                AudioPlay(
                    viewModel = viewModel,
                    audioFile = file,
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = uiState.textGenerated)
                }
            }
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
