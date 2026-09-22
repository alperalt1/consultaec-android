package org.universalworldtechnologyec.cedulamovil.data.remote.interceptor;

import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  public AuthInterceptor_Factory(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(userPreferencesDataStoreProvider.get());
  }

  public static AuthInterceptor_Factory create(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    return new AuthInterceptor_Factory(userPreferencesDataStoreProvider);
  }

  public static AuthInterceptor newInstance(DataStore<UserPreferences> userPreferencesDataStore) {
    return new AuthInterceptor(userPreferencesDataStore);
  }
}
