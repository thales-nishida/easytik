package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.thalesnishida.makeeasytik.model.Keys
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.ui.state.SettingsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModelImpl @Inject constructor(
    private val cacheService: CacheService
) : ViewModel(), SettingsViewModel {
    private val _uiState: MutableStateFlow<SettingsUIState> = MutableStateFlow(SettingsUIState())
    private val _toastMessage: MutableSharedFlow<String> = MutableSharedFlow()
    override val uiState: StateFlow<SettingsUIState> get() = _uiState
    override val toastMessage: SharedFlow<String> get() = _toastMessage

    override fun updateApiGemini(keyGemini: String) {
        _uiState.update {
            it.copy(
                apiKeyGemini = keyGemini
            )
        }
    }

    override fun updateApiElevenLabs(keyElevenLabs: String) {
        _uiState.update {
            it.copy(
                apiKeyElevenLabs = keyElevenLabs
            )
        }
    }

    override fun saveApiKey() {
        viewModelScope.launch {
            val geminiInput = _uiState.value.apiKeyGemini
            val elevenLabsInput = _uiState.value.apiKeyElevenLabs

            if (geminiInput.isBlank() && elevenLabsInput.isBlank()) {
                _toastMessage.emit("Os campos não pode estar em branco")
                return@launch
            }

            val keyCached = cacheService.getApiKeys()
            val updateKeys = Keys (
                keyGemini = geminiInput.ifBlank { keyCached?.keyGemini.orEmpty() },
                keyElevenLabs = elevenLabsInput.ifBlank { keyCached?.keyElevenLabs.orEmpty() }
            )

            cacheService.setApiKeys(updateKeys)
            _toastMessage.emit("Keys adicionado com sucesso!")
        }
    }

}