package com.ways2u.config;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigModule_ProvideBaseUrlFactory implements Factory<String> {
  private final ConfigModule module;

  public ConfigModule_ProvideBaseUrlFactory(ConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public String get() {
    return Preconditions.checkNotNull(
        module.provideBaseUrl(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<String> create(ConfigModule module) {
    return new ConfigModule_ProvideBaseUrlFactory(module);
  }
}
