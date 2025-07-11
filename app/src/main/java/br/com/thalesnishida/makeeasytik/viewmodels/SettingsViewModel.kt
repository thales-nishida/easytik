package br.com.thalesnishida.makeeasytik.viewmodels

import br.com.thalesnishida.makeeasytik.ui.state.SettingsUIState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SettingsViewModel {
    val uiState: StateFlow<SettingsUIState>
    val toastMessage: SharedFlow<String>
    fun updateApiGemini(keyGemini: String)
    fun updateApiElevenLabs(keyElevenLabs: String)
    fun saveApiKey()
}