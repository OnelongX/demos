package com.ways2u;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideAppContextFactory implements Factory<AppContext> {
  private final AppModule module;

  public AppModule_ProvideAppContextFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public AppContext get() {
    return Preconditions.checkNotNull(
        module.provideAppContext(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<AppContext> create(AppModule module) {
    return new AppModule_ProvideAppContextFactory(module);
  }
}
