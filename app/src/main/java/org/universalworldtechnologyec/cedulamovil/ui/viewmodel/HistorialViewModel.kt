package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarConsultaDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed interface HistorialUiState {
    data object Loading : HistorialUiState
    data class Success(val consultas: List<ListarConsultaDomain>) : HistorialUiState
    data class Error(val message: String) : HistorialUiState
    data object Empty : HistorialUiState
}

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow<HistorialUiState>(HistorialUiState.Loading)
    val uiState: StateFlow<HistorialUiState> = _uiState.asStateFlow()

    init {
        _searchQuery
            .debounce(500L)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                flow {
                    emit(HistorialUiState.Loading)
                    val result = homeRepository.listar(ListarRequestDomain(cedula = query.trim()))
                    result.onSuccess { response ->
                        if (response.data.isEmpty()) {
                            emit(HistorialUiState.Empty)
                        } else {
                            emit(HistorialUiState.Success(response.data))
                        }
                    }.onFailure { error ->
                        emit(HistorialUiState.Error(error.localizedMessage ?: "Error al cargar historial"))
                    }
                }
            }
            .onEach { state ->
                _uiState.value = state
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }
}