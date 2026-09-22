package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConsultarResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ListarConsultaDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.SuscripcionResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val suscripcion: SuscripcionResponseDomain) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

sealed interface ConsultarUiState {
    data object Idle : ConsultarUiState
    data object Loading : ConsultarUiState
    data class Success(val resultado: ConsultarResponseDomain) : ConsultarUiState
    data class Error(val message: String) : ConsultarUiState
}

sealed interface RecientesUiState {
    data object Loading : RecientesUiState
    data class Success(val consultas: List<ListarConsultaDomain>) : RecientesUiState
    data class Error(val message: String) : RecientesUiState
    data object Empty : RecientesUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>,
    private val appConfigDataStore: AppConfigDataStore,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _consultarUiState = MutableStateFlow<ConsultarUiState>(ConsultarUiState.Idle)
    val consultarUiState: StateFlow<ConsultarUiState> = _consultarUiState.asStateFlow()

    private val _recientesUiState = MutableStateFlow<RecientesUiState>(RecientesUiState.Loading)
    val recientesUiState: StateFlow<RecientesUiState> = _recientesUiState.asStateFlow()

    init {
        imprimirToken()
        obtenerSuscripcion()
        obtenerRecientes()
    }

    fun obtenerSuscripcion() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            homeRepository.suscripcion()
                .onSuccess { suscripcionDomain ->
                    _uiState.value = HomeUiState.Success(suscripcionDomain)
                }
                .onFailure { error ->
                    val mensajeError = error.localizedMessage ?: "Error al obtener la suscripción"
                    Log.e("HOME_VM", "Error al cargar suscripción: $mensajeError")
                    _uiState.value = HomeUiState.Error(mensajeError)
                }
        }
    }

    fun obtenerRecientes() {
        viewModelScope.launch {
            _recientesUiState.value = RecientesUiState.Loading
            homeRepository.recientes()
                .onSuccess { response ->
                    if (response.data.isEmpty()) {
                        _recientesUiState.value = RecientesUiState.Empty
                    } else {
                        _recientesUiState.value = RecientesUiState.Success(response.data)
                    }
                }
                .onFailure { error ->
                    _recientesUiState.value = RecientesUiState.Error(
                        error.localizedMessage ?: "Error al cargar las consultas recientes"
                    )
                }
        }
    }

    fun realizarConsulta(cedula: String) {
        if (cedula.length != 10) {
            _consultarUiState.value = ConsultarUiState.Error("La cédula debe contener exactamente 10 dígitos")
            return
        }

        viewModelScope.launch {
            _consultarUiState.value = ConsultarUiState.Loading
            homeRepository.consultar(ConsultarRequestDomain(identificacion = cedula))
                .onSuccess { response ->
                    _consultarUiState.value = ConsultarUiState.Success(response)
                    obtenerSuscripcion()
                    obtenerRecientes()
                }
                .onFailure { error ->
                    _consultarUiState.value = ConsultarUiState.Error(
                        error.localizedMessage ?: "Error al realizar la consulta"
                    )
                }
        }
    }

    fun resetConsultarState() {
        _consultarUiState.value = ConsultarUiState.Idle
    }

    private fun imprimirToken() {
        viewModelScope.launch {
            val token = userPreferencesDataStore.data.first().token
            if (token.isNotEmpty()) {
                Log.e("TOKEN", "Token descifrado y recuperado: $token")
            } else {
                Log.e("TOKEN", "EL TOKEN ESTÁ VACÍO")
            }
        }
    }
}