package com.example.travelworld.data;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class SharedPrefsManager_Factory implements Factory<SharedPrefsManager> {
  private final Provider<SharedPreferences> preferencesProvider;

  private final Provider<Context> contextProvider;

  public SharedPrefsManager_Factory(Provider<SharedPreferences> preferencesProvider,
      Provider<Context> contextProvider) {
    this.preferencesProvider = preferencesProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPrefsManager get() {
    return newInstance(preferencesProvider.get(), contextProvider.get());
  }

  public static SharedPrefsManager_Factory create(Provider<SharedPreferences> preferencesProvider,
      Provider<Context> contextProvider) {
    return new SharedPrefsManager_Factory(preferencesProvider, contextProvider);
  }

  public static SharedPrefsManager newInstance(SharedPreferences preferences, Context context) {
    return new SharedPrefsManager(preferences, context);
  }
}
