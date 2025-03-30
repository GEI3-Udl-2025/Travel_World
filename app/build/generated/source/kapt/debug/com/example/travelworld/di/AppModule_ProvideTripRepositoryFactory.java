package com.example.travelworld.di;

import com.example.travelworld.data.local.dao.SubTripDao;
import com.example.travelworld.data.local.dao.TripDao;
import com.example.travelworld.domain.repository.TripRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
  private final Provider<TripDao> tripDaoProvider;

  private final Provider<SubTripDao> subTripDaoProvider;

  public AppModule_ProvideTripRepositoryFactory(Provider<TripDao> tripDaoProvider,
      Provider<SubTripDao> subTripDaoProvider) {
    this.tripDaoProvider = tripDaoProvider;
    this.subTripDaoProvider = subTripDaoProvider;
  }

  @Override
  public TripRepository get() {
    return provideTripRepository(tripDaoProvider.get(), subTripDaoProvider.get());
  }

  public static AppModule_ProvideTripRepositoryFactory create(Provider<TripDao> tripDaoProvider,
      Provider<SubTripDao> subTripDaoProvider) {
    return new AppModule_ProvideTripRepositoryFactory(tripDaoProvider, subTripDaoProvider);
  }

  public static TripRepository provideTripRepository(TripDao tripDao, SubTripDao subTripDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTripRepository(tripDao, subTripDao));
  }
}
