package org.universalworldtechnologyec.cedulamovil.ui.viewmodel;

import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore;
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
public final class SettingViewModel_Factory implements Factory<SettingViewModel> {
  private final Provider<AppConfigDataStore> appConfigDataStoreProvider;

  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  public SettingViewModel_Factory(Provider<AppConfigDataStore> appConfigDataStoreProvider,
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    this.appConfigDataStoreProvider = appConfigDataStoreProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public SettingViewModel get() {
    return newInstance(appConfigDataStoreProvider.get(), userPreferencesDataStoreProvider.get());
  }

  public static SettingViewModel_Factory create(
      Provider<AppConfigDataStore> appConfigDataStoreProvider,
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider) {
    return new SettingViewModel_Factory(appConfigDataStoreProvider, userPreferencesDataStoreProvider);
  }

  public static SettingViewModel newInstance(AppConfigDataStore appConfigDataStore,
      DataStore<UserPreferences> userPreferencesDataStore) {
    return new SettingViewModel(appConfigDataStore, userPreferencesDataStore);
  }
}
