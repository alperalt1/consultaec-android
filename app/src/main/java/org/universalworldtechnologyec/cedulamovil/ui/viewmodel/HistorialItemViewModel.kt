package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.DetalleResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HistorialItemUiState {
    data object Loading : HistorialItemUiState
    data class Success(val detalle: DetalleResponseDomain) : HistorialItemUiState
    data class Error(val message: String) : HistorialItemUiState
}

@HiltViewModel
class HistorialItemViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HistorialItemUiState>(HistorialItemUiState.Loading)
    val uiState: StateFlow<HistorialItemUiState> = _uiState.asStateFlow()

    private var lastLoadedId: String? = null

    fun obtenerDetalle(idConsulta: String) {
        if (lastLoadedId == idConsulta && _uiState.value is HistorialItemUiState.Success) {
            return
        }
        if (idConsulta.isBlank()) {
            _uiState.value = HistorialItemUiState.Error("ID de consulta inválido")
            return
        }

        viewModelScope.launch {
            _uiState.value = HistorialItemUiState.Loading
            homeRepository.detalle(DetalleRequestDomain(id = idConsulta))
                .onSuccess { response ->
                    lastLoadedId = idConsulta
                    _uiState.value = HistorialItemUiState.Success(response)
                }
                .onFailure { error ->
                    lastLoadedId = null
                    _uiState.value = HistorialItemUiState.Error(
                        error.localizedMessage ?: "Error al obtener detalle de la consulta"
                    )
                }
        }
    }
}