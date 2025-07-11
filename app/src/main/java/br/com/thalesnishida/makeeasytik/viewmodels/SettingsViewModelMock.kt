package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.ui.state.SettingsUIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModelMock : ViewModel(), SettingsViewModel {
    private val _uiState: MutableStateFlow<SettingsUIState> = MutableStateFlow(SettingsUIState())
    private val _toastMessage: MutableSharedFlow<String> = MutableSharedFlow()
    override val uiState: StateFlow<SettingsUIState> get() = _uiState
    override val toastMessage: SharedFlow<String> get() = _toastMessage

    override fun updateApiGemini(keyGemini: String) {}
    override fun updateApiElevenLabs(keyElevenLabs: String) {}
    override fun saveApiKey() {}
}