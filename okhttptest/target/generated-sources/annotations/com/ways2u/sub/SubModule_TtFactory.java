package com.ways2u.sub;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SubModule_TtFactory implements Factory<String> {
  private final SubModule module;

  public SubModule_TtFactory(SubModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public String get() {
    return Preconditions.checkNotNull(
        module.tt(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<String> create(SubModule module) {
    return new SubModule_TtFactory(module);
  }
}
