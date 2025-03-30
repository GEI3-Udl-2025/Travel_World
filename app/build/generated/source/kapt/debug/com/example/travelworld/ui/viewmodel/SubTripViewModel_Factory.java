package com.example.travelworld.ui.viewmodel;

import androidx.lifecycle.SavedStateHandle;
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
public final class SubTripViewModel_Factory implements Factory<SubTripViewModel> {
  private final Provider<TripRepository> repositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public SubTripViewModel_Factory(Provider<TripRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.repositoryProvider = repositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public SubTripViewModel get() {
    return newInstance(repositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static SubTripViewModel_Factory create(Provider<TripRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new SubTripViewModel_Factory(repositoryProvider, savedStateHandleProvider);
  }

  public static SubTripViewModel newInstance(TripRepository repository,
      SavedStateHandle savedStateHandle) {
    return new SubTripViewModel(repository, savedStateHandle);
  }
}
