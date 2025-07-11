package br.com.thalesnishida.makeeasytik.viewmodels

import br.com.thalesnishida.makeeasytik.ui.state.LoginUIState
import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val uiState: StateFlow<LoginUIState>
}