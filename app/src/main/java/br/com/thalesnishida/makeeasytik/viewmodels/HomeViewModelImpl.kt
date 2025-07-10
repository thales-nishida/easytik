package br.com.thalesnishida.makeeasytik.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.services.GeminiService
import br.com.thalesnishida.makeeasytik.ui.state.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val cacheService: CacheService,
    private val geminiService: GeminiService
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
            }catch (e: Exception) {
                //TODO(create a dialog for exibe the error)
                e.printStackTrace()
            }finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}