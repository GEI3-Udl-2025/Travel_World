package com.example.travelworld.ui.viewmodel;

import com.example.travelworld.data.SharedPrefsManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class UserPreferencesViewModel_Factory implements Factory<UserPreferencesViewModel> {
  private final Provider<SharedPrefsManager> sharedPrefsManagerProvider;

  public UserPreferencesViewModel_Factory(Provider<SharedPrefsManager> sharedPrefsManagerProvider) {
    this.sharedPrefsManagerProvider = sharedPrefsManagerProvider;
  }

  @Override
  public UserPreferencesViewModel get() {
    return newInstance(sharedPrefsManagerProvider.get());
  }

  public static UserPreferencesViewModel_Factory create(
      Provider<SharedPrefsManager> sharedPrefsManagerProvider) {
    return new UserPreferencesViewModel_Factory(sharedPrefsManagerProvider);
  }

  public static UserPreferencesViewModel newInstance(SharedPrefsManager sharedPrefsManager) {
    return new UserPreferencesViewModel(sharedPrefsManager);
  }
}
