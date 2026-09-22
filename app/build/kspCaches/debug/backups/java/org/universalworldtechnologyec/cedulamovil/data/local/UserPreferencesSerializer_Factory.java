package org.universalworldtechnologyec.cedulamovil.data.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.security.CryptoManager;

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
public final class UserPreferencesSerializer_Factory implements Factory<UserPreferencesSerializer> {
  private final Provider<CryptoManager> cryptoManagerProvider;

  public UserPreferencesSerializer_Factory(Provider<CryptoManager> cryptoManagerProvider) {
    this.cryptoManagerProvider = cryptoManagerProvider;
  }

  @Override
  public UserPreferencesSerializer get() {
    return newInstance(cryptoManagerProvider.get());
  }

  public static UserPreferencesSerializer_Factory create(
      Provider<CryptoManager> cryptoManagerProvider) {
    return new UserPreferencesSerializer_Factory(cryptoManagerProvider);
  }

  public static UserPreferencesSerializer newInstance(CryptoManager cryptoManager) {
    return new UserPreferencesSerializer(cryptoManager);
  }
}
