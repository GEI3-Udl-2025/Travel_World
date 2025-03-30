package com.example.travelworld.ui.viewmodel;

import com.example.travelworld.domain.repository.TripRepository;
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
public final class TripViewModel_Factory implements Factory<TripViewModel> {
  private final Provider<TripRepository> repositoryProvider;

  public TripViewModel_Factory(Provider<TripRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public TripViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static TripViewModel_Factory create(Provider<TripRepository> repositoryProvider) {
    return new TripViewModel_Factory(repositoryProvider);
  }

  public static TripViewModel newInstance(TripRepository repository) {
    return new TripViewModel(repository);
  }
}
