package com.ways2u.user;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class Binder_Factory implements Factory<Binder> {
  private final Provider<User> userProvider;

  public Binder_Factory(Provider<User> userProvider) {
    assert userProvider != null;
    this.userProvider = userProvider;
  }

  @Override
  public Binder get() {
    return new Binder(userProvider.get());
  }

  public static Factory<Binder> create(Provider<User> userProvider) {
    return new Binder_Factory(userProvider);
  }
}
