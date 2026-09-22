package org.universalworldtechnologyec.cedulamovil.data.di;

import android.content.Context;
import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.local.UserPreferencesSerializer;
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataStoreModule_ProvideUserPreferencesDataStoreFactory implements Factory<DataStore<UserPreferences>> {
  private final Provider<Context> contextProvider;

  private final Provider<UserPreferencesSerializer> userPreferencesSerializerProvider;

  public DataStoreModule_ProvideUserPreferencesDataStoreFactory(Provider<Context> contextProvider,
      Provider<UserPreferencesSerializer> userPreferencesSerializerProvider) {
    this.contextProvider = contextProvider;
    this.userPreferencesSerializerProvider = userPreferencesSerializerProvider;
  }

  @Override
  public DataStore<UserPreferences> get() {
    return provideUserPreferencesDataStore(contextProvider.get(), userPreferencesSerializerProvider.get());
  }

  public static DataStoreModule_ProvideUserPreferencesDataStoreFactory create(
      Provider<Context> contextProvider,
      Provider<UserPreferencesSerializer> userPreferencesSerializerProvider) {
    return new DataStoreModule_ProvideUserPreferencesDataStoreFactory(contextProvider, userPreferencesSerializerProvider);
  }

  public static DataStore<UserPreferences> provideUserPreferencesDataStore(Context context,
      UserPreferencesSerializer userPreferencesSerializer) {
    return Preconditions.checkNotNullFromProvides(DataStoreModule.INSTANCE.provideUserPreferencesDataStore(context, userPreferencesSerializer));
  }
}
