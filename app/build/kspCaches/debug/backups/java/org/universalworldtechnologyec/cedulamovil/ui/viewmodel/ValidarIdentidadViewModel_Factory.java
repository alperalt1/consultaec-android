package org.universalworldtechnologyec.cedulamovil.ui.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository;

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
public final class ValidarIdentidadViewModel_Factory implements Factory<ValidarIdentidadViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SavedStateHandle> savedSateteHandleProvider;

  public ValidarIdentidadViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedSateteHandleProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.savedSateteHandleProvider = savedSateteHandleProvider;
  }

  @Override
  public ValidarIdentidadViewModel get() {
    return newInstance(authRepositoryProvider.get(), savedSateteHandleProvider.get());
  }

  public static ValidarIdentidadViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider,
      Provider<SavedStateHandle> savedSateteHandleProvider) {
    return new ValidarIdentidadViewModel_Factory(authRepositoryProvider, savedSateteHandleProvider);
  }

  public static ValidarIdentidadViewModel newInstance(AuthRepository authRepository,
      SavedStateHandle savedSateteHandle) {
    return new ValidarIdentidadViewModel(authRepository, savedSateteHandle);
  }
}
