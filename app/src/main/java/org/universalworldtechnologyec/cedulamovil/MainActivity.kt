package org.universalworldtechnologyec.cedulamovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.universalworldtechnologyec.cedulamovil.ui.navigation.AppNavigation
import org.universalworldtechnologyec.cedulamovil.ui.theme.ConsultaECTheme
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.NavigationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appNavigationViewModel: AppNavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            appNavigationViewModel.navigationState.value is NavigationState.Loading
        }

        setContent {
            ConsultaECTheme {
                AppNavigation(viewModel = appNavigationViewModel)
            }
        }
    }
}

