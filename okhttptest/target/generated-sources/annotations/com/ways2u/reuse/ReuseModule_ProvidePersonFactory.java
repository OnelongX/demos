package com.ways2u.reuse;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ReuseModule_ProvidePersonFactory implements Factory<Person> {
  private final ReuseModule module;

  public ReuseModule_ProvidePersonFactory(ReuseModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Person get() {
    return Preconditions.checkNotNull(
        module.providePerson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Person> create(ReuseModule module) {
    return new ReuseModule_ProvidePersonFactory(module);
  }
}
