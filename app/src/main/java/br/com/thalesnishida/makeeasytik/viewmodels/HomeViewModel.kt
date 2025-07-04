package br.com.thalesnishida.makeeasytik.viewmodels

import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val uiState: StateFlow<HomeUIState>
    fun updateNameTheme(value: String)
    fun sendTheme(theme: String)
}