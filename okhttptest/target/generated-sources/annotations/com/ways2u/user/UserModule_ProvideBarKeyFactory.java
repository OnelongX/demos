package com.ways2u.user;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserModule_ProvideBarKeyFactory implements Factory<String> {
  private final UserModule module;

  public UserModule_ProvideBarKeyFactory(UserModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public String get() {
    return Preconditions.checkNotNull(
        module.provideBarKey(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<String> create(UserModule module) {
    return new UserModule_ProvideBarKeyFactory(module);
  }
}
