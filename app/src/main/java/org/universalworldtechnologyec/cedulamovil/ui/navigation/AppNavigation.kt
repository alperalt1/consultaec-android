package org.universalworldtechnologyec.cedulamovil.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.universalworldtechnologyec.cedulamovil.ui.view.ChangePasswordView
import org.universalworldtechnologyec.cedulamovil.ui.view.ForgotPasswordView
import org.universalworldtechnologyec.cedulamovil.ui.view.HistorialItemView
import org.universalworldtechnologyec.cedulamovil.ui.view.HistorialView
import org.universalworldtechnologyec.cedulamovil.ui.view.HomeView
import org.universalworldtechnologyec.cedulamovil.ui.view.LoginView
import org.universalworldtechnologyec.cedulamovil.ui.view.PoliticsPrivacityView
import org.universalworldtechnologyec.cedulamovil.ui.view.RegisterView
import org.universalworldtechnologyec.cedulamovil.ui.view.SettingsView
import org.universalworldtechnologyec.cedulamovil.ui.view.SuportView
import org.universalworldtechnologyec.cedulamovil.ui.view.TermsAndConditionsView
import org.universalworldtechnologyec.cedulamovil.ui.view.ValidarIdentidadView
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.NavigationState

@Composable
fun AppNavigation(
    viewModel: AppNavigationViewModel = hiltViewModel()
) {

    val navigationState by viewModel.navigationState.collectAsStateWithLifecycle()

    when (val state = navigationState) {
        is NavigationState.Loading -> {
            Box(modifier = Modifier.fillMaxSize())
        }
        is NavigationState.Success -> {
            val backStack = rememberNavBackStack(state.startDestination)

            val onBack: () -> Unit = {
                if (backStack.size > 1) {
                    backStack.removeAt(backStack.lastIndex)
                }
            }

            NavDisplay(
                backStack = backStack,
                onBack = onBack,
                entryProvider = entryProvider {
                    entry<Screen.Login> {
                        LoginView(
                            navigateToForgotPass = {
                                backStack.add(Screen.SendRecoveryEmail)
                            },
                            navigateToRegister = {
                                backStack.add(Screen.Register)
                            },
                            navigateToHome = {
                                backStack.clear()
                                backStack.add(Screen.Home)
                            },
                            navigateToConfirmEmail = { email ->
                                backStack.add(Screen.ValidarIdentidad(email = email))
                            }
                        )
                    }

                    entry<Screen.Home> {
                        HomeView(
                            navigateToSetting = {
                                backStack.add(Screen.Settings)
                            },
                            navigateToHistory = {
                                backStack.add(Screen.Historial)
                            },
                            navigateToDetail = { id ->
                                backStack.add(Screen.HistorialDetail(id = id.toString()))
                            }
                        )
                    }

                    entry<Screen.SendRecoveryEmail> {
                        ForgotPasswordView(
                            navigateToChangePassword = { email ->
                                backStack.add(Screen.ChangePassword(email))
                            },
                            navigateToHome = {
                                backStack.clear()
                                backStack.add(Screen.Login)
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.Register> {
                        RegisterView(
                            navigateToHome = {
                                backStack.clear()
                                backStack.add(Screen.Login)
                            },
                            navigateToTerms = {
                                backStack.add(Screen.TermsAndConditions)
                            },
                            navigateToPolitics = {
                                backStack.add(Screen.PoliticsPrivacity)
                            },
                            navigateToConfirmEmail = { email ->
                                backStack.add(Screen.ValidarIdentidad(email))
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.TermsAndConditions> {
                        TermsAndConditionsView(
                            onBack = onBack
                        )
                    }

                    entry<Screen.PoliticsPrivacity> {
                        PoliticsPrivacityView(
                            onBack = onBack
                        )
                    }

                    entry<Screen.Suport> {
                        SuportView(
                            onBack = onBack
                        )
                    }

                    entry<Screen.ValidarIdentidad> { entry ->
                        ValidarIdentidadView(
                            email = entry.email,
                            navigateToLogin = {
                                backStack.clear()
                                backStack.add(Screen.Login)
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.ChangePassword> { entry ->
                        ChangePasswordView(
                            email = entry.email,
                            navigateToLogin = {
                                backStack.clear()
                                backStack.add(Screen.Login)
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.Settings> { entry ->
                        SettingsView(
                            navigateToLogin = {
                                backStack.clear()
                                backStack.add(Screen.Login)
                            },
                            navigateToPolitics = {
                                backStack.add(Screen.PoliticsPrivacity)
                            },
                            navigateToTerms = {
                                backStack.add(Screen.TermsAndConditions)
                            },
                            navigateToSuport = {
                                backStack.add(Screen.Suport)
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.Historial> { entry ->
                        HistorialView(
                            navigateToInfo = { id ->
                                backStack.add(Screen.HistorialDetail(id = id.toString()))
                            },
                            onBack = onBack
                        )
                    }

                    entry<Screen.HistorialDetail> {entry  ->
                        HistorialItemView(
                            id = entry.id,
                            onBack = onBack
                        )
                    }
                }
            )
        }
    }
}