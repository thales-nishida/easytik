package br.com.thalesnishida.makeeasytik.ui.state

import br.com.thalesnishida.makeeasytik.utils.StringUtils
import java.io.File

data class HomeUIState(
    val nameTheme: String = StringUtils.EMPTY,
    val showNameThemeError: Boolean = false,
    val textGenerated: String = StringUtils.EMPTY,
    val isLoading: Boolean = false,
    val enableButton: Boolean = false,
    val listAudios: List<File> = emptyList(),
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false,
    val audioFile: String = StringUtils.EMPTY
)