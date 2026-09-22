package org.universalworldtechnologyec.cedulamovil.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomButtonSignIn
import org.universalworldtechnologyec.cedulamovil.ui.view.components.CustomInput


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateForgotPasswordView() {

    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "Recuperar Contraseña",
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

                            },
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = "Icono para retroceder"
                        )
                    }

                }

            )
        }
    ) {innerPadding ->

        Column(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding(),
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
            Icon(
                modifier = Modifier.size(190.dp),
                imageVector = Icons.Filled.LockReset,
                contentDescription = "Icono de resetear password"
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 13.sp,
                textAlign = TextAlign.Justify,
                text = "Ingresa tu correo electrónico para enviarle el código de recuperación"
            )
            CustomInput(
                value = "",
                onChange = {  },
                enable =true,
                placeholder = "",
                tipoKeyboard = KeyboardType.Text,
                leadingIcon = Icons.Filled.Mail,
                isError = false,
                errorMsg ="",
                colorText = Color.Black
            )
            CustomButtonSignIn(
                name = "Enviar Código",
                isLoading = false,
                onClick = {
                    focusManager.clearFocus()
//                    viewModel.onEvent(ForgotPasswordUiEvent.Submit)
                }
            )
        }

    }
}