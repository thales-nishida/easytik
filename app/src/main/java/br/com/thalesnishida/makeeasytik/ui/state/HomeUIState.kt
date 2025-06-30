package br.com.thalesnishida.makeeasytik.ui.state

import br.com.thalesnishida.makeeasytik.utils.StringUtils

data class HomeUIState(
    val nameTheme: String = StringUtils.EMPTY,
    val showNameThemeError: Boolean = false,
)