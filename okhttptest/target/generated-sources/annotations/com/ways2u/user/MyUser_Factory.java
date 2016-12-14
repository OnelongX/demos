package com.ways2u.user;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class MyUser_Factory implements Factory<MyUser> {
  private static final MyUser_Factory INSTANCE = new MyUser_Factory();

  @Override
  public MyUser get() {
    return new MyUser();
  }

  public static Factory<MyUser> create() {
    return INSTANCE;
  }
}
