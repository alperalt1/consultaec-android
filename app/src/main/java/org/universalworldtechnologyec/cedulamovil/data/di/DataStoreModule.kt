package org.universalworldtechnologyec.cedulamovil.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import org.universalworldtechnologyec.cedulamovil.data.local.UserPreferencesSerializer
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences>{
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile("app_config_preferences")
            }
        )
    }

    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            produceFile = { context.dataStoreFile("user_preferences.pb")}
        )
    }
}