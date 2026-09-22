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
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomButtonSignIn
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomInput
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterUiEffect
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterUiEvent
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterUiState
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    navigateToHome: ()-> Unit,
    navigateToTerms:()-> Unit,
    navigateToPolitics:()-> Unit,
    navigateToConfirmEmail: (String)->Unit,
    onBack: ()-> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val formState by viewModel.registerUiForm.collectAsStateWithLifecycle()
    val uiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { event ->
            when (event) {
                is RegisterUiEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is RegisterUiEffect.NavigateToConfirmEmail -> {
                    navigateToConfirmEmail(event.email)
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
                        text = "Registrar",
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
                                viewModel.onEvent(RegisterUiEvent.ClearForm)
                                onBack()
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
                        text = "Ingresa tu nombre completo, un correo electrónico válido y tu contraseña para crear una nueva cuenta.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 13.sp
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            CustomInput(
                value = formState.fullName,
                onChange = { viewModel.onEvent(RegisterUiEvent.EnterFullName(it)) },
                enable = uiState !is RegisterUiState.Loading,
                placeholder = "",
                label = "NOMBRES",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Mail,
                isError = formState.fullNameError != null,
                errorMsg = formState.fullNameError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            CustomInput(
                value = formState.email,
                onChange = { viewModel.onEvent(RegisterUiEvent.EnterEmail(it)) },
                enable = uiState !is RegisterUiState.Loading,
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
                onChange = { viewModel.onEvent(RegisterUiEvent.EnterPassword(it)) },
                enable = uiState !is RegisterUiState.Loading,
                placeholder = "",
                label = "CONTRASEÑA",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Password,
                isPass = true,
                showPass = formState.isVisibility,
                onTogglePassword = { viewModel.onEvent(RegisterUiEvent.TogglePasswordVisibility) },
                isError = formState.passwordError != null,
                errorMsg = formState.passwordError,
                colorText = Color.Black
            )
            Spacer(Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = formState.aceptarPoliticas,
                    onCheckedChange = {
                        viewModel.onEvent(RegisterUiEvent.TogglePolitic)
                    }
                )
                val annotatedString = buildAnnotatedString {
                    append("Acepto los ")

                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "TERMS",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            ),
                            linkInteractionListener = {
                                navigateToTerms()
                            }
                        )
                    ) {
                        append("Términos y Condiciones")
                    }

                    append(" y las ")

                    withLink(
                        LinkAnnotation.Clickable(
                            tag = "PRIVACY",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            ),
                            linkInteractionListener = {
                                navigateToPolitics()
                            }
                        )
                    ) {
                        append("Políticas de Privacidad")
                    }
                }

                Text(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(Modifier.height(5.dp))
            CustomButtonSignIn(
                name = "Registrarse",
                isLoading = uiState is RegisterUiState.Loading,
                onClick = {
                    focusManager.clearFocus()
                    viewModel.onEvent(RegisterUiEvent.Submit)

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
                    text = "¿Ya tienes tienes una cuenta?"
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ){
                            viewModel.onEvent(RegisterUiEvent.ClearForm)
                            onBack()
                        },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    text = "Inicia Sesión"
                )
            }
        }
    }
}