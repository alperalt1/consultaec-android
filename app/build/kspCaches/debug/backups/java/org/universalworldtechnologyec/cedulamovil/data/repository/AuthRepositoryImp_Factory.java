package org.universalworldtechnologyec.cedulamovil.data.repository;

import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore;
import org.universalworldtechnologyec.cedulamovil.data.remote.AuthApiService;
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences;

@ScopeMetadata
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
public final class AuthRepositoryImp_Factory implements Factory<AuthRepositoryImp> {
  private final Provider<AuthApiService> authApiServiceProvider;

  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  private final Provider<AppConfigDataStore> appConfigDataStoreProvider;

  public AuthRepositoryImp_Factory(Provider<AuthApiService> authApiServiceProvider,
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider,
      Provider<AppConfigDataStore> appConfigDataStoreProvider) {
    this.authApiServiceProvider = authApiServiceProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
    this.appConfigDataStoreProvider = appConfigDataStoreProvider;
  }

  @Override
  public AuthRepositoryImp get() {
    return newInstance(authApiServiceProvider.get(), userPreferencesDataStoreProvider.get(), appConfigDataStoreProvider.get());
  }

  public static AuthRepositoryImp_Factory create(Provider<AuthApiService> authApiServiceProvider,
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider,
      Provider<AppConfigDataStore> appConfigDataStoreProvider) {
    return new AuthRepositoryImp_Factory(authApiServiceProvider, userPreferencesDataStoreProvider, appConfigDataStoreProvider);
  }

  public static AuthRepositoryImp newInstance(AuthApiService authApiService,
      DataStore<UserPreferences> userPreferencesDataStore, AppConfigDataStore appConfigDataStore) {
    return new AuthRepositoryImp(authApiService, userPreferencesDataStore, appConfigDataStore);
  }
}
