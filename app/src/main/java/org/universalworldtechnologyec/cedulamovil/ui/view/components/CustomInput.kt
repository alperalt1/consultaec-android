package org.universalworldtechnologyec.cedulamovil.ui.view.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomInput(
    value: String = "",
    onChange: (String)-> Unit = {},
    placeholder: String? = "",
    onlyRead: Boolean = false,
    enable: Boolean = true,
    isPass: Boolean = false,
    showPass: Boolean = false,
    onTogglePassword: () -> Unit = {},
    label: String? = "",
    tipoKeyboard: KeyboardType = KeyboardType.Text,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    errorMsg: String? = null,
    colorText: Color
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 7.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if(label != null){
            Text(
                label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W500,
                color = colorText,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        TextField(
            value = value,
            readOnly = onlyRead,
            enabled = enable,
            onValueChange = onChange,
            placeholder = {
                if(placeholder != null){
                    Text(
                        text = placeholder,
                        color = Color.Gray)
                }
            },
            isError = isError,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge,
            visualTransformation = if (isPass && !showPass) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = tipoKeyboard,autoCorrectEnabled = false),
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                when {
                    isPass -> {
                        IconButton(onClick = onTogglePassword) {
                            Icon(
                                imageVector = if (showPass) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = if (showPass) "Ocultar contraseña" else "Mostrar contraseña",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    trailingIcon != null -> {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            leadingIcon = leadingIcon?.let { icon ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        AnimatedVisibility(
            visible = isError && !errorMsg.isNullOrBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = errorMsg.orEmpty(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 3.dp, top = 3.dp)
            )
        }
    }

}