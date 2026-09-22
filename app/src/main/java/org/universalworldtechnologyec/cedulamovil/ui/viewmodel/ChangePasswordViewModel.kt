package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.ChangePasswordRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ChangePasswordResponseDomain
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

sealed interface ChangePasswordUiState {
    data object Idle : ChangePasswordUiState
    data object Loading : ChangePasswordUiState
    data class Success(val response: ChangePasswordResponseDomain) : ChangePasswordUiState
    data class Error(val msg: String) : ChangePasswordUiState
}

data class ChangePasswordUiForm(
    val email: String = "",
    val otp: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val otpError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
)

sealed interface ChangePasswordUiEvent {
    data class EnterOtp(val value: String) : ChangePasswordUiEvent
    data class EnterPassword(val value: String) : ChangePasswordUiEvent
    data class EnterConfirmPassword(val value: String) : ChangePasswordUiEvent
    data object TogglePasswordVisibility : ChangePasswordUiEvent
    data object ToggleConfirmPasswordVisibility : ChangePasswordUiEvent
    data object Submit : ChangePasswordUiEvent
}

sealed interface ChangePasswordUiEffect {
    data class ShowToast(val message: String) : ChangePasswordUiEffect
    data object NavigateToLogin : ChangePasswordUiEffect
}

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val emailArg = savedStateHandle.get<String>("email") ?: ""

    private val _formUiState = MutableStateFlow(ChangePasswordUiForm(email = emailArg))
    val formUiState: StateFlow<ChangePasswordUiForm> = _formUiState.asStateFlow()

    private val _uiState = MutableStateFlow<ChangePasswordUiState>(ChangePasswordUiState.Idle)
    val uiState: StateFlow<ChangePasswordUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<ChangePasswordUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun setEmail(email: String) {
        _formUiState.update { it.copy(email = email) }
    }

    fun onEvent(event: ChangePasswordUiEvent) {
        when (event) {
            is ChangePasswordUiEvent.EnterOtp -> {
                _formUiState.update { it.copy(otp = event.value, otpError = null) }
            }
            is ChangePasswordUiEvent.EnterPassword -> {
                _formUiState.update { it.copy(password = event.value, passwordError = null) }
            }
            is ChangePasswordUiEvent.EnterConfirmPassword -> {
                _formUiState.update { it.copy(confirmPassword = event.value, confirmPasswordError = null) }
            }
            is ChangePasswordUiEvent.TogglePasswordVisibility -> {
                _formUiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is ChangePasswordUiEvent.ToggleConfirmPasswordVisibility -> {
                _formUiState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
            }
            is ChangePasswordUiEvent.Submit -> onSubmit()
        }
    }

    private fun onSubmit() {
        val current = _formUiState.value
        val isOtpEmpty = current.otp.isBlank()
        val isPasswordEmpty = current.password.isBlank()
        val isPasswordShort = current.password.length <= 6
        val arePasswordsMismatch = current.password != current.confirmPassword

        _formUiState.update {
            it.copy(
                otpError = if (isOtpEmpty) "Ingresa el código enviado." else null,
                passwordError = when {
                    isPasswordEmpty -> "Ingresa la nueva contraseña."
                    isPasswordShort -> "Debe tener más de 6 caracteres."
                    else -> null
                },
                confirmPasswordError = if (arePasswordsMismatch) "Las contraseñas no coinciden." else null
            )
        }

        if (isOtpEmpty || isPasswordEmpty || isPasswordShort || arePasswordsMismatch) return

        val request = ChangePasswordRequestDomain(
            email = current.email,
            otp = current.otp,
            password = current.password,
            confirmPassword = current.confirmPassword
        )

        viewModelScope.launch {
            _uiState.value = ChangePasswordUiState.Loading
            val result = authRepository.changepassword(request)
            result.onSuccess { response ->
                _uiState.value = ChangePasswordUiState.Success(response)
                _uiEffect.send(ChangePasswordUiEffect.ShowToast(response.message))
                _uiEffect.send(ChangePasswordUiEffect.NavigateToLogin)
            }.onFailure { error ->
                val errorMsg = error.message ?: "Error al cambiar la contraseña."
                _uiState.value = ChangePasswordUiState.Error(errorMsg)
                _uiEffect.send(ChangePasswordUiEffect.ShowToast(errorMsg))
            }
        }
    }
}