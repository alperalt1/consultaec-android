package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.RegisterRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.RegisterResponseDomain
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

sealed interface UpdateForgotPasswordUiState {
    data object Idle: UpdateForgotPasswordUiState
    data object Loading: UpdateForgotPasswordUiState
    data class Success(val success: RegisterResponseDomain): UpdateForgotPasswordUiState
    data class Error(val msg: String): UpdateForgotPasswordUiState
}

data class UpdateForgotPasswordUiForm(
    val email: String = "",
    val isVisibility: Boolean = false,
    val aceptarPoliticas: Boolean = false,
    val fullNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)

sealed interface UpdateForgotPasswordUiEvent{
    data class EnterEmail(val value: String): UpdateForgotPasswordUiEvent
    data object Submit: UpdateForgotPasswordUiEvent
    data object ClearForm: UpdateForgotPasswordUiEvent
}

sealed interface UpdateForgotPasswordUiEffect {
    data class ShowToast(val message: String) : UpdateForgotPasswordUiEffect
}


@HiltViewModel
class UpdateForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _updateForgotPasswordUiForm = MutableStateFlow<UpdateForgotPasswordUiForm>(UpdateForgotPasswordUiForm())
    val updateForgotPasswordUiForm: StateFlow<UpdateForgotPasswordUiForm> = _updateForgotPasswordUiForm.asStateFlow()

    private val _updateForgotPasswordUiState = MutableStateFlow<UpdateForgotPasswordUiState>(UpdateForgotPasswordUiState.Idle)
    val updateForgotPasswordUiState: StateFlow<UpdateForgotPasswordUiState> = _updateForgotPasswordUiState.asStateFlow()

    private val _uiEffect = Channel<UpdateForgotPasswordUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()


    fun onEvent(event: UpdateForgotPasswordUiEvent){
        when(event){

            is UpdateForgotPasswordUiEvent.EnterEmail -> {
                _updateForgotPasswordUiForm.update {
                    it.copy(
                        email = event.value,
                        emailError = null
                    )
                }
            }

            is UpdateForgotPasswordUiEvent.Submit -> {
                onSubmit()
            }

            is UpdateForgotPasswordUiEvent.ClearForm -> {
                _updateForgotPasswordUiForm.update {
                    UpdateForgotPasswordUiForm()
                }
            }
        }
    }

    private fun onSubmit(){
        val current = _updateForgotPasswordUiForm.value
        val isEmail = current.email.isBlank()

        _updateForgotPasswordUiForm.update {
            it.copy(
                emailError =  if(isEmail) "Por favor, ingresa un correo." else null,
            )
        }

        val isValid: Boolean = !isEmail
        if (!isValid) return

        val request = RegisterRequestDomain(
            fullName = "",
            email = current.email,
            password = ""
        )

        viewModelScope.launch {
            _updateForgotPasswordUiState.value = UpdateForgotPasswordUiState.Loading
            val response = authRepository.register(request)
            response.onSuccess {
//                _registerUiState.value = RegisterUiState.Success(it)
//                _registerUiForm.update {
//                    it.copy(
//                        fullName = "",
//                        email = "",
//                        password = "",
//                        isVisibility = false
//                    )
//                }
                _uiEffect.send(UpdateForgotPasswordUiEffect.ShowToast("Registro de usuario realizado exitosamente."))
            }.onFailure { error ->
                val errorMsg = error.message ?: "Error al registrarse."
                _updateForgotPasswordUiState.value = UpdateForgotPasswordUiState.Error(errorMsg)
                _uiEffect.send(UpdateForgotPasswordUiEffect.ShowToast(errorMsg))
            }
        }
    }
}