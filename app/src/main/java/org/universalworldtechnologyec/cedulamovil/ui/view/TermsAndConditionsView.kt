package org.universalworldtechnologyec.cedulamovil.ui.view

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
fun TermsAndConditionsView(
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
                        text = "Términos y Condiciones",
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
                    title = "1. Aceptación de Uso",
                    body = "Al utilizar esta aplicación, el usuario acepta que las consultas realizadas tienen un fin estrictamente informativo y personal. El uso indebido de la información es responsabilidad exclusiva del usuario."
                )

                LegalSection(
                    title = "2. Origen de la Información",
                    body = "Los datos mostrados provienen de accesos a servicios externos de información. Esta aplicación no altera, modifica ni almacena datos oficiales, funcionando únicamente como un visualizador de información disponible públicamente."
                )

                LegalSection(
                    title = "3. No Validez Oficial",
                    body = "Se aclara que la información presentada no tiene validez legal como documento de identidad físico o certificado oficial emitido por el Registro Civil de Ecuador."
                )

                LegalSection(
                    title = "4. Almacenamiento y Registro",
                    body = "Para su comodidad y control de cuenta, la aplicación mantiene un historial de las consultas realizadas vinculado a su perfil. Esta información se almacena de forma segura en nuestros servidores y solo es accesible por usted a través de su cuenta personal."
                )

                LegalSection(
                    title = "5. Uso Responsable",
                    body = "El usuario se compromete a utilizar la aplicación únicamente con fines personales y legales. Queda prohibido el uso de la información para acoso, discriminación, fraude o cualquier actividad ilícita. El usuario es responsable del uso que haga de la información consultada dentro de la aplicación."
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}