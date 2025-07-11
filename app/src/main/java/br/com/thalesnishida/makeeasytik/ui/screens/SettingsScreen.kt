package br.com.thalesnishida.makeeasytik.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import br.com.thalesnishida.makeeasytik.composables.ButtonDefault
import br.com.thalesnishida.makeeasytik.composables.OutlinedTextFieldDefault
import br.com.thalesnishida.makeeasytik.viewmodels.SettingsViewModel
import br.com.thalesnishida.makeeasytik.viewmodels.SettingsViewModelMock
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.onEach { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }.launchIn(this)
    }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextFieldDefault(
            value = uiState.apiKeyGemini,
            onValueChange = { viewModel.updateApiGemini(it) },
            label = "Digite a api do Gemini",
            isError = false
        )

        OutlinedTextFieldDefault(
            value = uiState.apiKeyElevenLabs,
            onValueChange = { viewModel.updateApiElevenLabs(it) },
            label = "Digite a api do ElevenLabs",
            isError = false
        )

        ButtonDefault(
            onClick = {
                viewModel.saveApiKey()
            }
        ) {
            Text("Atualizar API Keys")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        viewModel = hiltViewModel<SettingsViewModelMock>()
    )
}