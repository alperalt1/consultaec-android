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
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider;

  private final Provider<AppConfigDataStore> appConfigDataStoreProvider;

  private final Provider<HomeRepository> homeRepositoryProvider;

  public HomeViewModel_Factory(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider,
      Provider<AppConfigDataStore> appConfigDataStoreProvider,
      Provider<HomeRepository> homeRepositoryProvider) {
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
    this.appConfigDataStoreProvider = appConfigDataStoreProvider;
    this.homeRepositoryProvider = homeRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(userPreferencesDataStoreProvider.get(), appConfigDataStoreProvider.get(), homeRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<DataStore<UserPreferences>> userPreferencesDataStoreProvider,
      Provider<AppConfigDataStore> appConfigDataStoreProvider,
      Provider<HomeRepository> homeRepositoryProvider) {
    return new HomeViewModel_Factory(userPreferencesDataStoreProvider, appConfigDataStoreProvider, homeRepositoryProvider);
  }

  public static HomeViewModel newInstance(DataStore<UserPreferences> userPreferencesDataStore,
      AppConfigDataStore appConfigDataStore, HomeRepository homeRepository) {
    return new HomeViewModel(userPreferencesDataStore, appConfigDataStore, homeRepository);
  }
}
