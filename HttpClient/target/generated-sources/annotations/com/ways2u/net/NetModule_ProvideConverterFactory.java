package com.ways2u.net;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Converter;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideConverterFactory implements Factory<Converter.Factory> {
  private final NetModule module;

  private final Provider<Gson> gsonProvider;

  public NetModule_ProvideConverterFactory(NetModule module, Provider<Gson> gsonProvider) {
    assert module != null;
    this.module = module;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public Converter.Factory get() {
    return Preconditions.checkNotNull(
        module.provideConverter(gsonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Converter.Factory> create(NetModule module, Provider<Gson> gsonProvider) {
    return new NetModule_ProvideConverterFactory(module, gsonProvider);
  }
}
