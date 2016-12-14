package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final NetModule module;

  private final Provider<Boolean> isDebugProvider;

  public NetModule_ProvideOkHttpClientFactory(NetModule module, Provider<Boolean> isDebugProvider) {
    assert module != null;
    this.module = module;
    assert isDebugProvider != null;
    this.isDebugProvider = isDebugProvider;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkHttpClient(isDebugProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(NetModule module, Provider<Boolean> isDebugProvider) {
    return new NetModule_ProvideOkHttpClientFactory(module, isDebugProvider);
  }
}
