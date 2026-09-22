package org.universalworldtechnologyec.cedulamovil.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomButtonSignIn
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomInput
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginUiEffect
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginUiEvent
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginUiState
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.universalworldtechnologyec.cedulamovil.R


@Composable
fun LoginView(
    navigateToForgotPass: ()->Unit,
    navigateToRegister: ()-> Unit,
    navigateToHome: ()-> Unit,
    navigateToConfirmEmail: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val formState by viewModel.loginUiForm.collectAsStateWithLifecycle()
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is LoginUiEffect.ShowToast -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    val job = launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                    delay(2000)
                    job.cancel()
                }
                is LoginUiEffect.NavigateToHome -> {
                    navigateToHome()
                }
                is LoginUiEffect.NavigateToConfirmEmail -> {
                    navigateToConfirmEmail(effect.email)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    containerColor = Color.DarkGray,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(10.dp),
                    action = {
                        data.visuals.actionLabel?.let { label ->
                            TextButton(onClick = { data.performAction() }) {
                                Text(text = label, color = Color.White)
                            }
                        }
                    }
                ) {
                    Text(text = data.visuals.message)
                }
            }
        }
    ) {innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = 18.dp,
                    end = 18.dp
                ).pointerInput(Unit){
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                }
            ,

            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Box(
                Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp)
                        .size(120.dp)
                    ,
                    painter = painterResource(R.drawable.logo),
                    contentDescription =  "Logo Aplicación"
                )
            }
            Spacer(Modifier.height(15.dp))
            Text(
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                text = "Consulta EC"
            )
            Spacer(Modifier.height(5.dp))
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Consulte información de forma ágil y segura"
            )
            Spacer(Modifier.height(25.dp))
            CustomInput(
                value = formState.email,
                onChange = { viewModel.onEvent(LoginUiEvent.EnterEmail(it)) },
                enable = uiState !is LoginUiState.Loading,
                placeholder = "",
                label = "CORREO ELECTRÓNICO",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Mail,
                isError = formState.emailError != null,
                errorMsg = formState.emailError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            CustomInput(
                value = formState.password,
                onChange = { viewModel.onEvent(LoginUiEvent.EnterPassword(it)) },
                enable =  uiState !is LoginUiState.Loading,
                placeholder = "",
                label = "CONTRASEÑA",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Password,
                isPass = true,
                showPass = formState.isPasswordVisible,
                onTogglePassword = { viewModel.onEvent(LoginUiEvent.TogglePasswordVisibility) },
                isError = formState.passwordError != null,
                errorMsg = formState.passwordError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(10.dp))
            Box(
                Modifier.fillMaxWidth(),

            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ){
                            navigateToForgotPass()
                        },
                    style = MaterialTheme.typography.bodySmall,
                    text = "¿Olvidó su contraseña?"
                )
            }
            Spacer(Modifier.height(10.dp))
            CustomButtonSignIn(
                name = "Iniciar Sesión",
                isLoading = uiState is LoginUiState.Loading,
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(LoginUiEvent.Submit)
                }
            )
            Spacer(Modifier.height(30.dp))
            Box(
                Modifier.fillMaxWidth(),

                ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodySmall,
                    text = "O"
                )
            }
            Spacer(Modifier.height(30.dp))
            Row(

                ){
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "¿Aún no tienes una cuenta?"
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ){
                            navigateToRegister()
                        },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    text = "Registrate aqui"
                )
            }
        }

    }
}