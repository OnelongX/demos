package com.ways2u.config;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigModule_ProvideOpenNetworkInterceptorFactory implements Factory<Boolean> {
  private final ConfigModule module;

  public ConfigModule_ProvideOpenNetworkInterceptorFactory(ConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Boolean get() {
    return Preconditions.checkNotNull(
        module.provideOpenNetworkInterceptor(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Boolean> create(ConfigModule module) {
    return new ConfigModule_ProvideOpenNetworkInterceptorFactory(module);
  }
}
