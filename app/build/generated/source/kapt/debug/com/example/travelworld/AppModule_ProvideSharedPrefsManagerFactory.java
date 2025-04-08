package com.example.travelworld;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.travelworld.data.SharedPrefsManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideSharedPrefsManagerFactory implements Factory<SharedPrefsManager> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  private final Provider<Context> contextProvider;

  public AppModule_ProvideSharedPrefsManagerFactory(
      Provider<SharedPreferences> sharedPreferencesProvider, Provider<Context> contextProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPrefsManager get() {
    return provideSharedPrefsManager(sharedPreferencesProvider.get(), contextProvider.get());
  }

  public static AppModule_ProvideSharedPrefsManagerFactory create(
      Provider<SharedPreferences> sharedPreferencesProvider, Provider<Context> contextProvider) {
    return new AppModule_ProvideSharedPrefsManagerFactory(sharedPreferencesProvider, contextProvider);
  }

  public static SharedPrefsManager provideSharedPrefsManager(SharedPreferences sharedPreferences,
      Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSharedPrefsManager(sharedPreferences, context));
  }
}
