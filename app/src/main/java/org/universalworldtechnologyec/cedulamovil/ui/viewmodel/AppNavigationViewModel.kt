package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
import org.universalworldtechnologyec.cedulamovil.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface NavigationState {
    object Loading : NavigationState
    data class Success(val startDestination: Screen) : NavigationState
}

@HiltViewModel
class AppNavigationViewModel @Inject constructor(
    private val userPreferencesDataStore: DataStore<UserPreferences>
): ViewModel() {

    private val _navigationState = MutableStateFlow<NavigationState>(NavigationState.Loading)
    val navigationState: StateFlow<NavigationState> = _navigationState.asStateFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            userPreferencesDataStore.data.collectLatest { preferences ->
                val token = preferences.token

                val startDestination = if (token.isNotBlank()) {
                    Screen.Home
                } else {
                    Screen.Login
                }

                _navigationState.value = NavigationState.Success(startDestination)
            }
        }
    }
}