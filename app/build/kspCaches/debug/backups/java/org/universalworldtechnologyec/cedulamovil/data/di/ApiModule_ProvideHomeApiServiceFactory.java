package org.universalworldtechnologyec.cedulamovil.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import org.universalworldtechnologyec.cedulamovil.data.remote.HomeApiService;
import retrofit2.Retrofit;

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
public final class ApiModule_ProvideHomeApiServiceFactory implements Factory<HomeApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideHomeApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public HomeApiService get() {
    return provideHomeApiService(retrofitProvider.get());
  }

  public static ApiModule_ProvideHomeApiServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new ApiModule_ProvideHomeApiServiceFactory(retrofitProvider);
  }

  public static HomeApiService provideHomeApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideHomeApiService(retrofit));
  }
}
