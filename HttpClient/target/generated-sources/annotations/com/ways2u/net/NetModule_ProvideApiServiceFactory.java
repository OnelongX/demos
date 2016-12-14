package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideApiServiceFactory implements Factory<GankApi> {
  private final NetModule module;

  private final Provider<Retrofit> restAdapterProvider;

  public NetModule_ProvideApiServiceFactory(
      NetModule module, Provider<Retrofit> restAdapterProvider) {
    assert module != null;
    this.module = module;
    assert restAdapterProvider != null;
    this.restAdapterProvider = restAdapterProvider;
  }

  @Override
  public GankApi get() {
    return Preconditions.checkNotNull(
        module.provideApiService(restAdapterProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<GankApi> create(NetModule module, Provider<Retrofit> restAdapterProvider) {
    return new NetModule_ProvideApiServiceFactory(module, restAdapterProvider);
  }
}
