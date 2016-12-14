package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.Interceptor;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideInterceptorFactory implements Factory<Interceptor> {
  private final NetModule module;

  public NetModule_ProvideInterceptorFactory(NetModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Interceptor get() {
    return Preconditions.checkNotNull(
        module.provideInterceptor(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Interceptor> create(NetModule module) {
    return new NetModule_ProvideInterceptorFactory(module);
  }
}
