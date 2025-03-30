package com.example.travelworld.di;

import android.content.Context;
import com.example.travelworld.data.local.TripDatabase;
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
public final class AppModule_ProvideTripDatabaseFactory implements Factory<TripDatabase> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideTripDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TripDatabase get() {
    return provideTripDatabase(contextProvider.get());
  }

  public static AppModule_ProvideTripDatabaseFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideTripDatabaseFactory(contextProvider);
  }

  public static TripDatabase provideTripDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTripDatabase(context));
  }
}
