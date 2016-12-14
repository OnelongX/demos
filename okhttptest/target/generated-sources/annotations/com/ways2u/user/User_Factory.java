package com.ways2u.user;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class User_Factory implements Factory<User> {
  private static final User_Factory INSTANCE = new User_Factory();

  @Override
  public User get() {
    return new User();
  }

  public static Factory<User> create() {
    return INSTANCE;
  }
}
