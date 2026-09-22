package org.universalworldtechnologyec.cedulamovil.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.remote.HomeApiService;

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
public final class HomeRepositoryImp_Factory implements Factory<HomeRepositoryImp> {
  private final Provider<HomeApiService> homeApiServiceProvider;

  public HomeRepositoryImp_Factory(Provider<HomeApiService> homeApiServiceProvider) {
    this.homeApiServiceProvider = homeApiServiceProvider;
  }

  @Override
  public HomeRepositoryImp get() {
    return newInstance(homeApiServiceProvider.get());
  }

  public static HomeRepositoryImp_Factory create(Provider<HomeApiService> homeApiServiceProvider) {
    return new HomeRepositoryImp_Factory(homeApiServiceProvider);
  }

  public static HomeRepositoryImp newInstance(HomeApiService homeApiService) {
    return new HomeRepositoryImp(homeApiService);
  }
}
