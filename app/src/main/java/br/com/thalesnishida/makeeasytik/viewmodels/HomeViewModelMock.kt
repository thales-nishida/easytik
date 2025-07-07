package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModelMock : ViewModel(), HomeViewModel {
    private val _uiSate: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    override val uiState: StateFlow<HomeUIState> get() = _uiSate

    override fun updateNameTheme(value: String) {}

    override fun sendTheme(theme: String) {}
}