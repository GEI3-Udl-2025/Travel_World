package com.example.travelworld.domain.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TripRepositoryImpl_Factory implements Factory<TripRepositoryImpl> {
  @Override
  public TripRepositoryImpl get() {
    return newInstance();
  }

  public static TripRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TripRepositoryImpl newInstance() {
    return new TripRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final TripRepositoryImpl_Factory INSTANCE = new TripRepositoryImpl_Factory();
  }
}
