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
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadUiEffect
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadUiEvent
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadUiState
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidarIdentidadView(
    email: String,
    navigateToLogin: ()-> Unit,
    onBack: ()-> Unit,
    viewModel: ValidarIdentidadViewModel = hiltViewModel()
) {

    val formState by viewModel.validarIdentidadUiForm.collectAsStateWithLifecycle()
    val uiState by viewModel.validarIdentidadUiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember  { SnackbarHostState() }

    LaunchedEffect(email) {
        viewModel.setEmail(email)
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { event ->
            when (event) {
                is ValidarIdentidadUiEffect.ShowToast -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    val job = launch {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                    delay(2000)
                    job.cancel()
                }
                is ValidarIdentidadUiEffect.NavigateToLogin -> {
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
                        text = "Validar",
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
                            modifier = Modifier.size(40.dp).clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ){
                                viewModel.onEvent(ValidarIdentidadUiEvent.ClearForm)
                                navigateToLogin()
                            },
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "Icono para retroceder"
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
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding(),
                    end = 18.dp,
                    start = 18.dp
                ).pointerInput(Unit){
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
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
                        text = "Ingresa el codigo que fue enviado a tu correo electrónico para validar tu identidad.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            CustomInput(
                value = formState.otp,
                onChange = { viewModel.onEvent(ValidarIdentidadUiEvent.EnterOtp(it)) },
                enable = uiState !is ValidarIdentidadUiState.Loading,
                placeholder = "",
                label = "CODIGO",
                tipoKeyboard = KeyboardType.Number,
                leadingIcon = Icons.Filled.Password,
                isError = formState.otpError != null,
                errorMsg = formState.otpError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            CustomButtonSignIn(
                name = "Validar",
                isLoading = uiState is ValidarIdentidadUiState.Loading,
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(ValidarIdentidadUiEvent.Submit)
                }
            )

        }
    }
}