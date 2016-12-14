package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import retrofit2.CallAdapter;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideCallAdapterFactory implements Factory<CallAdapter.Factory> {
  private final NetModule module;

  public NetModule_ProvideCallAdapterFactory(NetModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public CallAdapter.Factory get() {
    return Preconditions.checkNotNull(
        module.provideCallAdapter(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<CallAdapter.Factory> create(NetModule module) {
    return new NetModule_ProvideCallAdapterFactory(module);
  }
}
