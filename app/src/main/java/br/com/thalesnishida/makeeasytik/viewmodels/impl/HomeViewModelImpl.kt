package br.com.thalesnishida.makeeasytik.viewmodels.impl

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cacheService: CacheService,
) : ViewModel(), HomeViewModel {
    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    override val uiState: StateFlow<HomeUIState> get() = _uiState

    override fun updateNameTheme(value: String) {
        _uiState.update {
            it.copy(
                nameTheme = value
            )
        }
    }
}