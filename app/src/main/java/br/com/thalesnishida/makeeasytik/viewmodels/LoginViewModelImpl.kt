package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.ui.state.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class LoginViewModelImpl : ViewModel(), LoginViewModel {
    private val _uiState: MutableStateFlow<LoginUIState> = MutableStateFlow(LoginUIState())
    override val uiState: StateFlow<LoginUIState> get() = _uiState

}