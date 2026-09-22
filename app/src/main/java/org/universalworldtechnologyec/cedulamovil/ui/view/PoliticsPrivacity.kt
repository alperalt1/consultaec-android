package org.universalworldtechnologyec.cedulamovil.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoliticsPrivacityView(
    onBack: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        text = "Políticas de Privacidad",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(12.dp))

                LegalSection(
                    title = "1. Información Recopilada",
                    body = "Recopilamos datos básicos de su perfil como nombre y correo electrónico para la gestión de su cuenta. Asimismo, registramos los números de identificación consultados para mantener un historial de consultas disponible únicamente para usted."
                )

                LegalSection(
                    title = "2. Uso de la Información",
                    body = "Los datos almacenados se utilizan con el fin de proporcionar el servicio de historial, mejorar la estabilidad de la plataforma y garantizar la seguridad de su sesión mediante tokens de autenticación."
                )

                LegalSection(
                    title = "3. Almacenamiento y Seguridad",
                    body = "Su información personal y registros de consulta se almacenan en servidores seguros con protocolos de encriptación. No vendemos ni compartimos su información con empresas terceras para fines comerciales o de marketing."
                )

                LegalSection(
                    title = "4. Sus Derechos",
                    body = "Usted tiene derecho a acceder a su información en cualquier momento. De igual manera, puede solicitar la eliminación definitiva de su cuenta y de todo su historial de registros comunicándose con nuestro equipo de soporte."
                )

                LegalSection(
                    title = "5. Cambios en esta Política",
                    body = "Nos reservamos el derecho de actualizar esta política según sea necesario para cumplir con cambios técnicos o legales. El uso continuado de la aplicación implica la aceptación de estas condiciones."
                )

                LegalSection(
                    title = "6. Servicios de Terceros",
                    body = "La aplicación utiliza servicios externos de consulta de datos, como APIs de terceros (por ejemplo, apiconsult), para obtener información mostrada al usuario. No somos una entidad gubernamental ni representamos a ninguna institución pública. La información proporcionada es de carácter referencial y depende de la disponibilidad y precisión de dichos servicios externos."
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun LegalSection(
    title: String,
    body: String
) {
    Column(
        modifier = Modifier.padding(bottom = 18.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }
}