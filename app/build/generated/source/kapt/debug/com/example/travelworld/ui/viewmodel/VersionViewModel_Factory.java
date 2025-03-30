package com.example.travelworld.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class VersionViewModel_Factory implements Factory<VersionViewModel> {
  @Override
  public VersionViewModel get() {
    return newInstance();
  }

  public static VersionViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VersionViewModel newInstance() {
    return new VersionViewModel();
  }

  private static final class InstanceHolder {
    private static final VersionViewModel_Factory INSTANCE = new VersionViewModel_Factory();
  }
}
