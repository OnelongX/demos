package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Converter;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideRetrofitServiceFactory implements Factory<JsonService> {
  private final NetModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<String> baseUrlProvider;

  private final Provider<Converter.Factory> converterProvider;

  public NetModule_ProvideRetrofitServiceFactory(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<Converter.Factory> converterProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
    assert baseUrlProvider != null;
    this.baseUrlProvider = baseUrlProvider;
    assert converterProvider != null;
    this.converterProvider = converterProvider;
  }

  @Override
  public JsonService get() {
    return Preconditions.checkNotNull(
        module.provideRetrofitService(
            okHttpClientProvider.get(), baseUrlProvider.get(), converterProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<JsonService> create(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<Converter.Factory> converterProvider) {
    return new NetModule_ProvideRetrofitServiceFactory(
        module, okHttpClientProvider, baseUrlProvider, converterProvider);
  }
}
