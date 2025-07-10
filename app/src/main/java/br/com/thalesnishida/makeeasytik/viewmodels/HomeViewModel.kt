package br.com.thalesnishida.makeeasytik.viewmodels

import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface HomeViewModel {
    val uiState: StateFlow<HomeUIState>
    val toastMessage: SharedFlow<String>
    fun updateNameTheme(value: String)
    fun sendTheme(theme: String)
    fun createAudio()
    fun playAudio(file: String)
    fun stopAudio()
    fun pauseAudio()
    fun resumeAudio()
    fun listAudio()
    fun downloadFile(file: File)
    fun deleteAudio(theme: String)
}