package org.universalworldtechnologyec.cedulamovil.data.local;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;

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
public final class AppConfigDataStore_Factory implements Factory<AppConfigDataStore> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private final Provider<Json> jsonProvider;

  public AppConfigDataStore_Factory(Provider<DataStore<Preferences>> dataStoreProvider,
      Provider<Json> jsonProvider) {
    this.dataStoreProvider = dataStoreProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public AppConfigDataStore get() {
    return newInstance(dataStoreProvider.get(), jsonProvider.get());
  }

  public static AppConfigDataStore_Factory create(
      Provider<DataStore<Preferences>> dataStoreProvider, Provider<Json> jsonProvider) {
    return new AppConfigDataStore_Factory(dataStoreProvider, jsonProvider);
  }

  public static AppConfigDataStore newInstance(DataStore<Preferences> dataStore, Json json) {
    return new AppConfigDataStore(dataStore, json);
  }
}
