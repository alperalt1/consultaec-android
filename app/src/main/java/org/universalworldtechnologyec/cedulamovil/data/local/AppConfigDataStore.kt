package org.universalworldtechnologyec.cedulamovil.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import org.universalworldtechnologyec.cedulamovil.data.remote.dto.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfigDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
) {
    private object Keys {
        val USER_CONFIG = stringPreferencesKey("user_config")
    }

    suspend fun saveUserConfig(userData: UserData){
        val jsonString = json.encodeToString(userData)
        dataStore.edit { preferences ->
            preferences[Keys.USER_CONFIG] = jsonString
        }
    }

    val userConfig: Flow<UserData?> = dataStore.data.map { preferences ->
        val jsonString = preferences[Keys.USER_CONFIG] ?: return@map null
        try {
            json.decodeFromString<UserData>(jsonString)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    suspend fun clearUserConfig(){
        dataStore.edit { preferences ->
            preferences.remove(Keys.USER_CONFIG)
        }
    }
}