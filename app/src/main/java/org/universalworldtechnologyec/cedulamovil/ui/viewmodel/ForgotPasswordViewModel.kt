package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.ForgotPasswordRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.ForgotPasswordResponseDomain
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

sealed interface ForgotPasswordUiState{
    data object Idle: ForgotPasswordUiState
    data object Loading: ForgotPasswordUiState
    data class Success(val success: ForgotPasswordResponseDomain): ForgotPasswordUiState
    data class Error(val msg: String): ForgotPasswordUiState
}
sealed interface ForgotPasswordUiEvent {
    data class EnterEmail(val value: String): ForgotPasswordUiEvent
    data object Submit: ForgotPasswordUiEvent
}
data class ForgotPasswordUiForm(
    val email: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)

sealed interface ForgotPasswordUiEffect {
    data class ShowToast(val message: String) : ForgotPasswordUiEffect
    data class NavigateToChangePassword(val email: String) : ForgotPasswordUiEffect
}

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _forgotPasswordUiForm = MutableStateFlow<ForgotPasswordUiForm>(ForgotPasswordUiForm())
    val forgotPasswordUiForm: StateFlow<ForgotPasswordUiForm> = _forgotPasswordUiForm.asStateFlow()

    private val _forgotPasswordUiState = MutableStateFlow<ForgotPasswordUiState>(ForgotPasswordUiState.Idle)
    val forgotPasswordUiState: StateFlow<ForgotPasswordUiState> = _forgotPasswordUiState.asStateFlow()

    private val _uiEffect = Channel<ForgotPasswordUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: ForgotPasswordUiEvent){
        when(event){

            is ForgotPasswordUiEvent.EnterEmail -> {
                _forgotPasswordUiForm.update {
                    it.copy(
                        email = event.value,
                        emailError = null
                    )
                }
            }
            is ForgotPasswordUiEvent.Submit -> {
                onSubmit()
            }

        }
    }

    private fun onSubmit(){
        val current = _forgotPasswordUiForm.value

        val isEmailBlank = current.email.isBlank()

        _forgotPasswordUiForm.update {
            it.copy(
                emailError = if (isEmailBlank) "Por favor, ingresa tu correo." else null,
            )
        }

        val isValid: Boolean = !isEmailBlank
        if(!isValid) return

        val request = ForgotPasswordRequestDomain(
            email = current.email,
        )

        viewModelScope.launch {
            _forgotPasswordUiState.value = ForgotPasswordUiState.Loading
            val response = authRepository.forgotpassword(request)

            response.onSuccess {
                val emailEnviado = current.email
                _forgotPasswordUiState.value = ForgotPasswordUiState.Success(it)
                _uiEffect.send(ForgotPasswordUiEffect.NavigateToChangePassword(emailEnviado))
            }.onFailure { error ->
                val errorMsg = error.message ?: "Error al enviar el correo de recuperación"
                _forgotPasswordUiState.value = ForgotPasswordUiState.Error(errorMsg)
                _uiEffect.send(ForgotPasswordUiEffect.ShowToast(errorMsg))
            }
        }
    }
}