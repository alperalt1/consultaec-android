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


sealed interface RegisterUiState {
    data object Idle: RegisterUiState
    data object Loading: RegisterUiState
    data class Success(val success: RegisterResponseDomain): RegisterUiState
    data class Error(val msg: String): RegisterUiState
}

data class RegisterUiForm(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val isVisibility: Boolean = false,
    val aceptarPoliticas: Boolean = false,
    val fullNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)

sealed interface RegisterUiEvent{
    data class EnterFullName(val value: String): RegisterUiEvent
    data class EnterEmail(val value: String): RegisterUiEvent
    data class EnterPassword(val value: String): RegisterUiEvent
    data object TogglePasswordVisibility: RegisterUiEvent
    data object TogglePolitic: RegisterUiEvent
    data object Submit: RegisterUiEvent
    data object ClearForm: RegisterUiEvent
}

sealed interface RegisterUiEffect {
    data class ShowToast(val message: String) : RegisterUiEffect
    data class NavigateToConfirmEmail(val email: String) : RegisterUiEffect
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _registerUiForm = MutableStateFlow<RegisterUiForm>(RegisterUiForm())
    val registerUiForm: StateFlow<RegisterUiForm> = _registerUiForm.asStateFlow()

    private val _registerUiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState.asStateFlow()

    private val _uiEffect = Channel<RegisterUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: RegisterUiEvent){
        when(event){
            is RegisterUiEvent.EnterFullName -> {
                _registerUiForm.update {
                    it.copy(
                        fullName = event.value,
                        fullNameError = null
                    )
                }
            }
            is RegisterUiEvent.EnterEmail -> {
                _registerUiForm.update {
                    it.copy(
                        email = event.value,
                        emailError = null
                    )
                }
            }
            is RegisterUiEvent.EnterPassword -> {
                _registerUiForm.update {
                    it.copy(
                        password = event.value,
                        passwordError = null
                    )
                }
            }
            is RegisterUiEvent.TogglePasswordVisibility -> {
                _registerUiForm.update {
                    it.copy(
                        isVisibility = !it.isVisibility
                    )
                }
            }
            is RegisterUiEvent.Submit -> {
                onSubmit()
            }
            is RegisterUiEvent.TogglePolitic -> {
                _registerUiForm.update {
                    it.copy(
                        aceptarPoliticas = !it.aceptarPoliticas
                    )
                }
            }
            is RegisterUiEvent.ClearForm -> {
                _registerUiForm.update {
                    RegisterUiForm()
                }
            }
        }
    }

    private fun onSubmit(){
        val current = _registerUiForm.value

        val isFullName = current.fullName.isBlank()
        val isEmail = current.email.isBlank()
        val isPassword = current.password.isBlank()
        val isPasswordLength = current.password.length <= 6

        _registerUiForm.update {
            it.copy(
                fullNameError = if(isFullName) "Por favor, ingresa un nombre completo." else null,
                emailError =  if(isEmail) "Por favor, ingresa un correo." else null,
                passwordError =  when {
                    isPassword -> "Por favor, ingresa una contraseña."
                    isPasswordLength -> "La contraseña debe tener más de 6 caracteres."
                    else -> null
                },
            )
        }

        val isValid: Boolean = !isFullName && !isEmail && !isPassword && !isPasswordLength
        if (!isValid) return

        if (!current.aceptarPoliticas){
            _registerUiState.value = RegisterUiState.Error("Debes aceptar los Términos y Condiciones")
            return
        }
        val request = RegisterRequestDomain(
            fullName = current.fullName,
            email = current.email,
            password = current.password
        )

        viewModelScope.launch {
            _registerUiState.value = RegisterUiState.Loading
            val response = authRepository.register(request)
            response.onSuccess {
                val emailRegistrado = current.email
                _registerUiState.value = RegisterUiState.Success(it)
                _registerUiForm.update {
                    it.copy(
                        fullName = "",
                        email = "",
                        password = "",
                        isVisibility = false
                    )
                }
               _uiEffect.send(RegisterUiEffect.NavigateToConfirmEmail(emailRegistrado))
            }.onFailure { error ->
                val errorMsg = error.message ?: "Error al registrarse."
                _registerUiState.value = RegisterUiState.Error(errorMsg)
                _uiEffect.send(RegisterUiEffect.ShowToast(errorMsg))
            }
        }
    }
}