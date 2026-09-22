package org.universalworldtechnologyec.cedulamovil.data.di;

import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.remote.interceptor.AuthInterceptor;
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<AuthInterceptor> {
  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  public NetworkModule_ProvideAuthInterceptorFactory(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public AuthInterceptor get() {
    return provideAuthInterceptor(userPreferencesDataStoreProvider.get());
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    return new NetworkModule_ProvideAuthInterceptorFactory(userPreferencesDataStoreProvider);
  }

  public static AuthInterceptor provideAuthInterceptor(
      DataStore<UserPreferences> userPreferencesDataStore) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthInterceptor(userPreferencesDataStore));
  }
}
