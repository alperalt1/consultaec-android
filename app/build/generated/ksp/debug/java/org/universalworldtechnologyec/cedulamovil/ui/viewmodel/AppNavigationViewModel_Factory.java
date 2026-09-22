package org.universalworldtechnologyec.cedulamovil.ui.viewmodel;

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
public final class AppNavigationViewModel_Factory implements Factory<AppNavigationViewModel> {
  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  public AppNavigationViewModel_Factory(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public AppNavigationViewModel get() {
    return newInstance(userPreferencesDataStoreProvider.get());
  }

  public static AppNavigationViewModel_Factory create(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    return new AppNavigationViewModel_Factory(userPreferencesDataStoreProvider);
  }

  public static AppNavigationViewModel newInstance(
      DataStore<UserPreferences> userPreferencesDataStore) {
    return new AppNavigationViewModel(userPreferencesDataStore);
  }
}
