package br.com.thalesnishida.makeeasytik.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.thalesnishida.makeeasytik.R
import br.com.thalesnishida.makeeasytik.services.AudioPlayService
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.services.ElevenLabsService
import br.com.thalesnishida.makeeasytik.services.GeminiService
import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cacheService: CacheService,
    private val geminiService: GeminiService,
    private val elevenLabsService: ElevenLabsService,
    private val audioPlayService: AudioPlayService
) : ViewModel(), HomeViewModel {
    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    private val _toastMessage: MutableSharedFlow<String> = MutableSharedFlow()
    override val uiState: StateFlow<HomeUIState> get() = _uiState
    override val toastMessage: SharedFlow<String> get() = _toastMessage

    override fun updateNameTheme(value: String) {
        _uiState.update {
            it.copy(
                nameTheme = value
            )
        }
    }

    override fun sendTheme(theme: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val results = geminiService.sendTheme(
                    theme = theme,
                    niche = "mistério",
                    tone = "casos de poilica"
                )

                _uiState.update {
                    it.copy(
                        textGenerated = results
                    )
                }
                _toastMessage.emit("Texto gerado com sucesso")
            } catch (e: Exception) {
                //TODO(create a dialog for exibe the error)
                e.printStackTrace()
            } finally {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        enableButton = true
                    )
                }
            }
        }
    }

    override fun createAudio() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_uiState.value.textGenerated.isNotBlank()) {
                    val text = _uiState.value.textGenerated
                    _uiState.update { it.copy(isLoading = true) }
                    elevenLabsService.sendTextToSpeech(
                        text = text,
                        theme = _uiState.value.nameTheme
                    )
                    _uiState.update { it.copy(listAudios = cacheService.getListAudio()) }
                }
            } catch (e: Exception) {
                _toastMessage.emit(context.getString(R.string.error_to_create_audio))
                e.printStackTrace()
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    override fun playAudio(file: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_uiState.value.isPaused && _uiState.value.audioFile == file) {
                    resumeAudio()
                    return@launch
                }
                _uiState.update {
                    it.copy(
                        audioFile = file
                    )
                }
                audioPlayService.play(file)
                _uiState.update {
                    it.copy(
                        isPlaying = true
                    )
                }
            } catch (e: Exception) {
                _toastMessage.emit("Error playing audio: ${e.message}")
            }
        }
    }

    override fun pauseAudio() {
        audioPlayService.pause()
        _uiState.update {
            it.copy(
                isPlaying = false,
                isPaused = true
            )
        }
    }

    override fun resumeAudio() {
        audioPlayService.resume()
        _uiState.update {
            it.copy(
                isPlaying = true,
            )
        }
    }

    override fun stopAudio() {
        audioPlayService.stop()
        _uiState.update {
            it.copy(
                isPlaying = false,
                isPaused = false,
            )
        }
    }

    override fun listAudio() {
        _uiState.update {
            it.copy(
                listAudios = cacheService.getListAudio()
            )
        }
    }

    override fun downloadFile(file: File) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val isDownloadAudio = audioPlayService.downloadAudioToPublicFolder(context, file)
            if (isDownloadAudio) {
                _toastMessage.emit("Download Realizado com sucesso.")
            } else {
                _toastMessage.emit("Não foi possivel fazer o download do audio.")
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    override fun deleteAudio(theme: String) {
        cacheService.deleteAudio(theme)
        _uiState.update {
            it.copy(listAudios = cacheService.getListAudio())
        }
    }
}