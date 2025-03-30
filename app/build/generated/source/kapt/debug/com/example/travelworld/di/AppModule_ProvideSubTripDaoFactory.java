package com.example.travelworld.di;

import com.example.travelworld.data.local.TripDatabase;
import com.example.travelworld.data.local.dao.SubTripDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideSubTripDaoFactory implements Factory<SubTripDao> {
  private final Provider<TripDatabase> dbProvider;

  public AppModule_ProvideSubTripDaoFactory(Provider<TripDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public SubTripDao get() {
    return provideSubTripDao(dbProvider.get());
  }

  public static AppModule_ProvideSubTripDaoFactory create(Provider<TripDatabase> dbProvider) {
    return new AppModule_ProvideSubTripDaoFactory(dbProvider);
  }

  public static SubTripDao provideSubTripDao(TripDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSubTripDao(db));
  }
}
