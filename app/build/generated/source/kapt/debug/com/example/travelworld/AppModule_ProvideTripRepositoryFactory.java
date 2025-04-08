package com.example.travelworld;

import com.example.travelworld.domain.repository.TripRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "cast"
})
public final class AppModule_ProvideTripRepositoryFactory implements Factory<TripRepository> {
  @Override
  public TripRepository get() {
    return provideTripRepository();
  }

  public static AppModule_ProvideTripRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TripRepository provideTripRepository() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTripRepository());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideTripRepositoryFactory INSTANCE = new AppModule_ProvideTripRepositoryFactory();
  }
}
