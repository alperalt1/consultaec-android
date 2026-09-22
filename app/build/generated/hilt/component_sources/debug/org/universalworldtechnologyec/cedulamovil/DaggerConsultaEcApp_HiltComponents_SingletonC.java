package org.universalworldtechnologyec.cedulamovil;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;
import org.universalworldtechnologyec.cedulamovil.data.di.ApiModule_ProvideAuthApiServiceFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.ApiModule_ProvideHomeApiServiceFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.DataStoreModule_ProvidePreferencesDataStoreFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.DataStoreModule_ProvideUserPreferencesDataStoreFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.NetworkModule_ProvideAuthInterceptorFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.NetworkModule_ProvideJsonFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.NetworkModule_ProvideOkHttpClientFactory;
import org.universalworldtechnologyec.cedulamovil.data.di.NetworkModule_ProvideRetrofitFactory;
import org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore;
import org.universalworldtechnologyec.cedulamovil.data.local.UserPreferencesSerializer;
import org.universalworldtechnologyec.cedulamovil.data.remote.AuthApiService;
import org.universalworldtechnologyec.cedulamovil.data.remote.HomeApiService;
import org.universalworldtechnologyec.cedulamovil.data.remote.interceptor.AuthInterceptor;
import org.universalworldtechnologyec.cedulamovil.data.repository.AuthRepositoryImp;
import org.universalworldtechnologyec.cedulamovil.data.repository.HomeRepositoryImp;
import org.universalworldtechnologyec.cedulamovil.data.security.CryptoManager;
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences;
import org.universalworldtechnologyec.cedulamovil.domain.repository.AuthRepository;
import org.universalworldtechnologyec.cedulamovil.domain.repository.HomeRepository;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ForgotPasswordViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ForgotPasswordViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ForgotPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ForgotPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialItemViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialItemViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialItemViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialItemViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HomeViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HomeViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.SettingViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.SettingViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.SettingViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.SettingViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.UpdateForgotPasswordViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.UpdateForgotPasswordViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.UpdateForgotPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.UpdateForgotPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel_HiltModules;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import retrofit2.Retrofit;

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
public final class DaggerConsultaEcApp_HiltComponents_SingletonC {
  private DaggerConsultaEcApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ConsultaEcApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ConsultaEcApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ConsultaEcApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ConsultaEcApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ConsultaEcApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ConsultaEcApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ConsultaEcApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ConsultaEcApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ConsultaEcApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ConsultaEcApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ConsultaEcApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ConsultaEcApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ConsultaEcApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(11).put(AppNavigationViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AppNavigationViewModel_HiltModules.KeyModule.provide()).put(ChangePasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ChangePasswordViewModel_HiltModules.KeyModule.provide()).put(ForgotPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ForgotPasswordViewModel_HiltModules.KeyModule.provide()).put(HistorialItemViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HistorialItemViewModel_HiltModules.KeyModule.provide()).put(HistorialViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HistorialViewModel_HiltModules.KeyModule.provide()).put(HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewModel_HiltModules.KeyModule.provide()).put(LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LoginViewModel_HiltModules.KeyModule.provide()).put(RegisterViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, RegisterViewModel_HiltModules.KeyModule.provide()).put(SettingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingViewModel_HiltModules.KeyModule.provide()).put(UpdateForgotPasswordViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, UpdateForgotPasswordViewModel_HiltModules.KeyModule.provide()).put(ValidarIdentidadViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ValidarIdentidadViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }
  }

  private static final class ViewModelCImpl extends ConsultaEcApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<AppNavigationViewModel> appNavigationViewModelProvider;

    Provider<ChangePasswordViewModel> changePasswordViewModelProvider;

    Provider<ForgotPasswordViewModel> forgotPasswordViewModelProvider;

    Provider<HistorialItemViewModel> historialItemViewModelProvider;

    Provider<HistorialViewModel> historialViewModelProvider;

    Provider<HomeViewModel> homeViewModelProvider;

    Provider<LoginViewModel> loginViewModelProvider;

    Provider<RegisterViewModel> registerViewModelProvider;

    Provider<SettingViewModel> settingViewModelProvider;

    Provider<UpdateForgotPasswordViewModel> updateForgotPasswordViewModelProvider;

    Provider<ValidarIdentidadViewModel> validarIdentidadViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.appNavigationViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.changePasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.forgotPasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.historialItemViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.historialViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.registerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.settingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.updateForgotPasswordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.validarIdentidadViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(11).put(AppNavigationViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) appNavigationViewModelProvider)).put(ChangePasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) changePasswordViewModelProvider)).put(ForgotPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) forgotPasswordViewModelProvider)).put(HistorialItemViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) historialItemViewModelProvider)).put(HistorialViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) historialViewModelProvider)).put(HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) homeViewModelProvider)).put(LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) loginViewModelProvider)).put(RegisterViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) registerViewModelProvider)).put(SettingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) settingViewModelProvider)).put(UpdateForgotPasswordViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) updateForgotPasswordViewModelProvider)).put(ValidarIdentidadViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) validarIdentidadViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.AppNavigationViewModel
          return (T) new AppNavigationViewModel(singletonCImpl.provideUserPreferencesDataStoreProvider.get());

          case 1: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ChangePasswordViewModel
          return (T) new ChangePasswordViewModel(singletonCImpl.providesAuthRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 2: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ForgotPasswordViewModel
          return (T) new ForgotPasswordViewModel(singletonCImpl.providesAuthRepositoryProvider.get());

          case 3: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialItemViewModel
          return (T) new HistorialItemViewModel(singletonCImpl.providesHomeRepositoryProvider.get());

          case 4: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HistorialViewModel
          return (T) new HistorialViewModel(singletonCImpl.providesHomeRepositoryProvider.get());

          case 5: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.HomeViewModel
          return (T) new HomeViewModel(singletonCImpl.provideUserPreferencesDataStoreProvider.get(), singletonCImpl.appConfigDataStoreProvider.get(), singletonCImpl.providesHomeRepositoryProvider.get());

          case 6: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.LoginViewModel
          return (T) new LoginViewModel(singletonCImpl.providesAuthRepositoryProvider.get());

          case 7: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.RegisterViewModel
          return (T) new RegisterViewModel(singletonCImpl.providesAuthRepositoryProvider.get());

          case 8: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.SettingViewModel
          return (T) new SettingViewModel(singletonCImpl.appConfigDataStoreProvider.get(), singletonCImpl.provideUserPreferencesDataStoreProvider.get());

          case 9: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.UpdateForgotPasswordViewModel
          return (T) new UpdateForgotPasswordViewModel(singletonCImpl.providesAuthRepositoryProvider.get());

          case 10: // org.universalworldtechnologyec.cedulamovil.ui.viewmodel.ValidarIdentidadViewModel
          return (T) new ValidarIdentidadViewModel(singletonCImpl.providesAuthRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ConsultaEcApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ConsultaEcApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ConsultaEcApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<CryptoManager> cryptoManagerProvider;

    Provider<DataStore<UserPreferences>> provideUserPreferencesDataStoreProvider;

    Provider<Json> provideJsonProvider;

    Provider<AuthInterceptor> provideAuthInterceptorProvider;

    Provider<OkHttpClient> provideOkHttpClientProvider;

    Provider<Retrofit> provideRetrofitProvider;

    Provider<AuthApiService> provideAuthApiServiceProvider;

    Provider<DataStore<Preferences>> providePreferencesDataStoreProvider;

    Provider<AppConfigDataStore> appConfigDataStoreProvider;

    Provider<AuthRepositoryImp> authRepositoryImpProvider;

    Provider<AuthRepository> providesAuthRepositoryProvider;

    Provider<HomeApiService> provideHomeApiServiceProvider;

    Provider<HomeRepositoryImp> homeRepositoryImpProvider;

    Provider<HomeRepository> providesHomeRepositoryProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    UserPreferencesSerializer userPreferencesSerializer() {
      return new UserPreferencesSerializer(cryptoManagerProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.cryptoManagerProvider = DoubleCheck.provider(new SwitchingProvider<CryptoManager>(singletonCImpl, 1));
      this.provideUserPreferencesDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<UserPreferences>>(singletonCImpl, 0));
      this.provideJsonProvider = DoubleCheck.provider(new SwitchingProvider<Json>(singletonCImpl, 5));
      this.provideAuthInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 7));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 6));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 4));
      this.provideAuthApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AuthApiService>(singletonCImpl, 3));
      this.providePreferencesDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<Preferences>>(singletonCImpl, 9));
      this.appConfigDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<AppConfigDataStore>(singletonCImpl, 8));
      this.authRepositoryImpProvider = new SwitchingProvider<>(singletonCImpl, 2);
      this.providesAuthRepositoryProvider = DoubleCheck.provider((Provider) authRepositoryImpProvider);
      this.provideHomeApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<HomeApiService>(singletonCImpl, 11));
      this.homeRepositoryImpProvider = new SwitchingProvider<>(singletonCImpl, 10);
      this.providesHomeRepositoryProvider = DoubleCheck.provider((Provider) homeRepositoryImpProvider);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @Override
    public void injectConsultaEcApp(ConsultaEcApp arg0) {
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // androidx.datastore.core.DataStore<org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences>
          return (T) DataStoreModule_ProvideUserPreferencesDataStoreFactory.provideUserPreferencesDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.userPreferencesSerializer());

          case 1: // org.universalworldtechnologyec.cedulamovil.data.security.CryptoManager
          return (T) new CryptoManager();

          case 2: // org.universalworldtechnologyec.cedulamovil.data.repository.AuthRepositoryImp
          return (T) new AuthRepositoryImp(singletonCImpl.provideAuthApiServiceProvider.get(), singletonCImpl.provideUserPreferencesDataStoreProvider.get(), singletonCImpl.appConfigDataStoreProvider.get());

          case 3: // org.universalworldtechnologyec.cedulamovil.data.remote.AuthApiService
          return (T) ApiModule_ProvideAuthApiServiceFactory.provideAuthApiService(singletonCImpl.provideRetrofitProvider.get());

          case 4: // retrofit2.Retrofit
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideJsonProvider.get(), singletonCImpl.provideOkHttpClientProvider.get());

          case 5: // kotlinx.serialization.json.Json
          return (T) NetworkModule_ProvideJsonFactory.provideJson();

          case 6: // okhttp3.OkHttpClient
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.provideAuthInterceptorProvider.get());

          case 7: // org.universalworldtechnologyec.cedulamovil.data.remote.interceptor.AuthInterceptor
          return (T) NetworkModule_ProvideAuthInterceptorFactory.provideAuthInterceptor(singletonCImpl.provideUserPreferencesDataStoreProvider.get());

          case 8: // org.universalworldtechnologyec.cedulamovil.data.local.AppConfigDataStore
          return (T) new AppConfigDataStore(singletonCImpl.providePreferencesDataStoreProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 9: // androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences>
          return (T) DataStoreModule_ProvidePreferencesDataStoreFactory.providePreferencesDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 10: // org.universalworldtechnologyec.cedulamovil.data.repository.HomeRepositoryImp
          return (T) new HomeRepositoryImp(singletonCImpl.provideHomeApiServiceProvider.get());

          case 11: // org.universalworldtechnologyec.cedulamovil.data.remote.HomeApiService
          return (T) ApiModule_ProvideHomeApiServiceFactory.provideHomeApiService(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
