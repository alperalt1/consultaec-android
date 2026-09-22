package org.universalworldtechnologyec.cedulamovil.ui.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.UserData
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val appConfigDataStore: AppConfigDataStore,
    private val userPreferencesDataStore: DataStore<UserPreferences>
): ViewModel() {
    val userData: StateFlow<UserData?> = appConfigDataStore.userConfig
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun logout(onLogoutSuccess: () -> Unit) {
        viewModelScope.launch {
            appConfigDataStore.clearUserConfig()
            userPreferencesDataStore.updateData { preferences ->
                preferences.toBuilder().clearToken().build()
            }
            onLogoutSuccess()
        }
    }
}