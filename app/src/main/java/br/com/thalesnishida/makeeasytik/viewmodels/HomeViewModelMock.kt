package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class HomeViewModelMock() : ViewModel(), HomeViewModel {
    private val _uiSate: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    private val _toastMessage: MutableSharedFlow<String> = MutableSharedFlow()
    override val toastMessage: SharedFlow<String> get() = _toastMessage
    override val uiState: StateFlow<HomeUIState> get() = _uiSate

    override fun updateNameTheme(value: String) {}
    override fun sendTheme(theme: String) {}
    override fun createAudio() {}
    override fun playAudio(file: String) {}
    override fun stopAudio() {}
    override fun pauseAudio() {}
    override fun resumeAudio() {}
    override fun listAudio() {}
    override fun downloadFile(file: File) {}
    override fun deleteAudio(theme: String) {}
}