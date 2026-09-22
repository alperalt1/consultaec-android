package org.universalworldtechnologyec.cedulamovil.ui.viewmodel;

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
public final class UpdateForgotPasswordViewModel_Factory implements Factory<UpdateForgotPasswordViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public UpdateForgotPasswordViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public UpdateForgotPasswordViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static UpdateForgotPasswordViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new UpdateForgotPasswordViewModel_Factory(authRepositoryProvider);
  }

  public static UpdateForgotPasswordViewModel newInstance(AuthRepository authRepository) {
    return new UpdateForgotPasswordViewModel(authRepository);
  }
}
