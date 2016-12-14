package com.ways2u.user;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserModule_ProvideUserFactory implements Factory<MyUser> {
  private final UserModule module;

  public UserModule_ProvideUserFactory(UserModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public MyUser get() {
    return Preconditions.checkNotNull(
        module.provideUser(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<MyUser> create(UserModule module) {
    return new UserModule_ProvideUserFactory(module);
  }
}
