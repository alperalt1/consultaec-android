package org.universalworldtechnologyec.cedulamovil.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.domain.model.ForgotPasswordRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.LoginRequestDomain
import org.universalworldtechnologyec.cedulamovil.domain.model.LoginResponseDomain
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

sealed interface LoginUiState{
    data object Idle: LoginUiState
    data object Loading: LoginUiState
    data class Success(val success: LoginResponseDomain): LoginUiState
    data class Error(val msg: String): LoginUiState
}
sealed interface LoginUiEvent {
    data class EnterEmail(val value: String): LoginUiEvent
    data class EnterPassword(val value: String): LoginUiEvent
    data object Submit: LoginUiEvent
    data object TogglePasswordVisibility: LoginUiEvent
}
data class LoginUiForm(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)

sealed interface LoginUiEffect {
    data class ShowToast(val message: String) : LoginUiEffect
    data object NavigateToHome : LoginUiEffect
    data class NavigateToConfirmEmail(val email: String) : LoginUiEffect
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _loginUiForm = MutableStateFlow<LoginUiForm>(LoginUiForm())
    val loginUiForm: StateFlow<LoginUiForm> = _loginUiForm.asStateFlow()

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _uiEffect = Channel<LoginUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.EnterEmail -> {
                _loginUiForm.update {
                    it.copy(
                        email = event.value,
                        emailError = null
                    )
                }
            }
            is LoginUiEvent.EnterPassword -> {
                _loginUiForm.update {
                    it.copy(
                        password = event.value,
                        passwordError = null
                    )
                }
            }
            is LoginUiEvent.TogglePasswordVisibility -> {
                _loginUiForm.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
            is LoginUiEvent.Submit -> {
                onSubmit()
            }

        }
    }

    private fun onSubmit(){
        val current = loginUiForm.value
        val isEmailBlank = current.email.isBlank()
        val isPasswordBlank = current.password.isBlank()

        _loginUiForm.update {
            it.copy(
                emailError = if (isEmailBlank) "Por favor, ingresa tu correo." else null,
                passwordError = if (isPasswordBlank) "Por favor, ingresa tu contraseña." else null
            )
        }

        val isValid: Boolean = !isEmailBlank && !isPasswordBlank

        if(!isValid) return

        val request = LoginRequestDomain(
            email = loginUiForm.value.email,
            password = loginUiForm.value.password
        )

        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            val response = authRepository.login(request)

            response.onSuccess { data ->
                _loginUiState.value = LoginUiState.Success(data)
                _loginUiForm.update {
                        LoginUiForm()
                }
                _uiEffect.send(LoginUiEffect.NavigateToHome)
            }.onFailure {error ->
                if (error is HttpException && error.code() == 403) {

                    authRepository.resendotp(ForgotPasswordRequestDomain(email = request.email))
                        .onSuccess {
                            _uiEffect.send(LoginUiEffect.ShowToast("Código de verificación reenviado a tu correo."))
                        }
                        .onFailure { resendError ->
                            val msg = resendError.message ?: "No se pudo reenviar el código de verificación."
                            _uiEffect.send(LoginUiEffect.ShowToast(msg))
                        }
                    _loginUiState.value = LoginUiState.Idle
                    _uiEffect.send(LoginUiEffect.NavigateToConfirmEmail(request.email))
                } else {
                    val errorMsg = error.message ?: "Error al iniciar sesión"
                    _loginUiState.value = LoginUiState.Error(errorMsg)
                    _uiEffect.send(LoginUiEffect.ShowToast(errorMsg))
                }
            }
        }
    }
}