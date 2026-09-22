package org.universalworldtechnologyec.cedulamovil.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomButtonSignIn
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomInput
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordUiEffect
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordUiEvent
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordUiState
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordView(
    email: String,
    navigateToLogin: () -> Unit,
    onBack: () -> Unit,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val formState by viewModel.formUiState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(email) {
        viewModel.setEmail(email)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ChangePasswordUiEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is ChangePasswordUiEffect.NavigateToLogin -> {
                    navigateToLogin()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "Cambiar Contraseña",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    Box(
                        Modifier
                            .padding(start = 10.dp)
                            .clip(RoundedCornerShape(7.dp))
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f)
                                ),
                                shape = RoundedCornerShape(7.dp)
                            )
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { onBack() },
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    end = 18.dp,
                    start = 18.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "Advertencia"
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Ingresa el código enviado a tu correo junto con tu nueva contraseña para actualizar la clave.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            CustomInput(
                value = formState.otp,
                onChange = { viewModel.onEvent(ChangePasswordUiEvent.EnterOtp(it)) },
                enable = uiState !is ChangePasswordUiState.Loading,
                label = "CÓDIGO DE RECUPERACIÓN",
                tipoKeyboard = KeyboardType.Number,
                leadingIcon = Icons.Filled.Password,
                isError = formState.otpError != null,
                errorMsg = formState.otpError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            CustomInput(
                value = formState.password,
                onChange = { viewModel.onEvent(ChangePasswordUiEvent.EnterPassword(it)) },
                enable = uiState !is ChangePasswordUiState.Loading,
                label = "NUEVA CONTRASEÑA",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Password,
                isPass = true,
                showPass = formState.isPasswordVisible,
                onTogglePassword = { viewModel.onEvent(ChangePasswordUiEvent.TogglePasswordVisibility) },
                isError = formState.passwordError != null,
                errorMsg = formState.passwordError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            CustomInput(
                value = formState.confirmPassword,
                onChange = { viewModel.onEvent(ChangePasswordUiEvent.EnterConfirmPassword(it)) },
                enable = uiState !is ChangePasswordUiState.Loading,
                label = "CONFIRMAR CONTRASEÑA",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Password,
                isPass = true,
                showPass = formState.isConfirmPasswordVisible,
                onTogglePassword = { viewModel.onEvent(ChangePasswordUiEvent.ToggleConfirmPasswordVisibility) },
                isError = formState.confirmPasswordError != null,
                errorMsg = formState.confirmPasswordError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(10.dp))
            CustomButtonSignIn(
                name = "Cambiar Contraseña",
                isLoading = uiState is ChangePasswordUiState.Loading,
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(ChangePasswordUiEvent.Submit)
                }
            )
        }
    }
}