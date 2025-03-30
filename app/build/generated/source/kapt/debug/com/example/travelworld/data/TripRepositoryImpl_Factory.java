package com.example.travelworld.data;

import com.example.travelworld.data.local.dao.SubTripDao;
import com.example.travelworld.data.local.dao.TripDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TripRepositoryImpl_Factory implements Factory<TripRepositoryImpl> {
  private final Provider<TripDao> tripDaoProvider;

  private final Provider<SubTripDao> subTripDaoProvider;

  public TripRepositoryImpl_Factory(Provider<TripDao> tripDaoProvider,
      Provider<SubTripDao> subTripDaoProvider) {
    this.tripDaoProvider = tripDaoProvider;
    this.subTripDaoProvider = subTripDaoProvider;
  }

  @Override
  public TripRepositoryImpl get() {
    return newInstance(tripDaoProvider.get(), subTripDaoProvider.get());
  }

  public static TripRepositoryImpl_Factory create(Provider<TripDao> tripDaoProvider,
      Provider<SubTripDao> subTripDaoProvider) {
    return new TripRepositoryImpl_Factory(tripDaoProvider, subTripDaoProvider);
  }

  public static TripRepositoryImpl newInstance(TripDao tripDao, SubTripDao subTripDao) {
    return new TripRepositoryImpl(tripDao, subTripDao);
  }
}
