package com.ways2u.config;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigModule_ProvideDebugFactory implements Factory<Boolean> {
  private final ConfigModule module;

  public ConfigModule_ProvideDebugFactory(ConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Boolean get() {
    return Preconditions.checkNotNull(
        module.provideDebug(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Boolean> create(ConfigModule module) {
    return new ConfigModule_ProvideDebugFactory(module);
  }
}
