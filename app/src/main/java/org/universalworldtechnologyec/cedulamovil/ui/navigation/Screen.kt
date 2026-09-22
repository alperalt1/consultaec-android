package org.universalworldtechnologyec.cedulamovil.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen: NavKey {
    @Serializable
    data object Login : Screen

    @Serializable
    data object Home: Screen

    @Serializable
    data object SendRecoveryEmail: Screen

    @Serializable
    data object Register: Screen

    @Serializable
    data object TermsAndConditions: Screen

    @Serializable
    data object PoliticsPrivacity: Screen

    @Serializable
    data class ValidarIdentidad(val email: String) : Screen

    @Serializable
    data class ChangePassword(val email: String): Screen

    @Serializable
    data object Settings: Screen

    @Serializable
    data object Suport: Screen

    @Serializable
    data object Historial: Screen

    @Serializable
    data class HistorialDetail(val id: String) : Screen
}