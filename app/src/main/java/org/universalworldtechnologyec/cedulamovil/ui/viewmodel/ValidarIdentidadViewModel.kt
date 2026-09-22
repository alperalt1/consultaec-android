package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.ConfirmEmailRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ConfirmEmailResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ValidarIdentidadUiState {
    data object Idle: ValidarIdentidadUiState
    data object Loading: ValidarIdentidadUiState
    data class Success(val success: ConfirmEmailResponseDomain): ValidarIdentidadUiState
    data class Error(val msg: String): ValidarIdentidadUiState
}

data class ValidarIdentidadUiForm(
    val otp: String = "",
    val email: String = "",
    val otpError: String? = null,
    val emailError: String? = null,
)

sealed interface ValidarIdentidadUiEvent{
    data class EnterOtp(val value: String): ValidarIdentidadUiEvent
    data object Submit: ValidarIdentidadUiEvent
    data object ClearForm: ValidarIdentidadUiEvent
}

sealed interface ValidarIdentidadUiEffect {
    data class ShowToast(val message: String) : ValidarIdentidadUiEffect
    data object NavigateToLogin : ValidarIdentidadUiEffect
}


@HiltViewModel
class ValidarIdentidadViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedSateteHandle: SavedStateHandle
): ViewModel() {

    private val emailArg = savedSateteHandle.get<String>("email") ?: ""
    private val _validarIdentidadUiForm = MutableStateFlow<ValidarIdentidadUiForm>(ValidarIdentidadUiForm(
        email = emailArg
    ))
    val validarIdentidadUiForm: StateFlow<ValidarIdentidadUiForm> = _validarIdentidadUiForm.asStateFlow()
    private val _validarIdentidadUiState = MutableStateFlow<ValidarIdentidadUiState>(
        ValidarIdentidadUiState.Idle)
    val validarIdentidadUiState: StateFlow<ValidarIdentidadUiState> = _validarIdentidadUiState.asStateFlow()

    private val _uiEffect = Channel<ValidarIdentidadUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: ValidarIdentidadUiEvent){
        when(event){
            is ValidarIdentidadUiEvent.EnterOtp -> {
                _validarIdentidadUiForm.update {
                    it.copy(
                        otp = event.value,
                        otpError = null
                    )
                }
            }
            is ValidarIdentidadUiEvent.Submit -> {
                onSubmit()
            }
            is ValidarIdentidadUiEvent.ClearForm -> {
                _validarIdentidadUiForm.update {
                    ValidarIdentidadUiForm()
                }
            }
        }
    }

    fun setEmail(email: String) {
        _validarIdentidadUiForm.update {
            it.copy(email = email)
        }
    }

    fun onSubmit(){
        val currency = _validarIdentidadUiForm.value
        if (currency.otp.isBlank()) {
            _validarIdentidadUiForm.update { it.copy(otpError = "Ingresa el código.") }
            return
        }
        val request = ConfirmEmailRequestDomain(
            otp = currency.otp,
            email = currency.email
        )
        viewModelScope.launch {
            _validarIdentidadUiState.value = ValidarIdentidadUiState.Loading
            val response = authRepository.validarIdentidad(request)
            response.onSuccess {
                _validarIdentidadUiState.value = ValidarIdentidadUiState.Success(it)
                _validarIdentidadUiForm.update {
                    it.copy(
                        email = "",
                        otp = ""
                    )
                }
                _uiEffect.send(ValidarIdentidadUiEffect.ShowToast("Activación de cuenta finalizada con exito."))
                _uiEffect.send(ValidarIdentidadUiEffect.NavigateToLogin)
            }.onFailure {error ->
                val errorMsg = error.message ?: "Error al validar correo eléctronico"
                _validarIdentidadUiState.value = ValidarIdentidadUiState.Error(errorMsg)
            }
        }
    }

}