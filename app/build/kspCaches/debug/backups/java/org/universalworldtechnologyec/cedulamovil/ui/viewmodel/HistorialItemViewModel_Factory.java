package org.universalworldtechnologyec.cedulamovil.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class HistorialItemViewModel_Factory implements Factory<HistorialItemViewModel> {
  private final Provider<HomeRepository> homeRepositoryProvider;

  public HistorialItemViewModel_Factory(Provider<HomeRepository> homeRepositoryProvider) {
    this.homeRepositoryProvider = homeRepositoryProvider;
  }

  @Override
  public HistorialItemViewModel get() {
    return newInstance(homeRepositoryProvider.get());
  }

  public static HistorialItemViewModel_Factory create(
      Provider<HomeRepository> homeRepositoryProvider) {
    return new HistorialItemViewModel_Factory(homeRepositoryProvider);
  }

  public static HistorialItemViewModel newInstance(HomeRepository homeRepository) {
    return new HistorialItemViewModel(homeRepository);
  }
}
