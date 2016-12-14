package com.ways2u.config;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigModule_TimeOutFactory implements Factory<Long> {
  private final ConfigModule module;

  public ConfigModule_TimeOutFactory(ConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Long get() {
    return Preconditions.checkNotNull(
        module.timeOut(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Long> create(ConfigModule module) {
    return new ConfigModule_TimeOutFactory(module);
  }
}
