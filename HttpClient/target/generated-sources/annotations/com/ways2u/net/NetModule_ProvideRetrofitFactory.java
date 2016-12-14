package com.ways2u.net;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final NetModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<String> baseUrlProvider;

  private final Provider<CallAdapter.Factory> callAdapterProvider;

  private final Provider<Converter.Factory> converterProvider;

  public NetModule_ProvideRetrofitFactory(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<CallAdapter.Factory> callAdapterProvider,
      Provider<Converter.Factory> converterProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
    assert baseUrlProvider != null;
    this.baseUrlProvider = baseUrlProvider;
    assert callAdapterProvider != null;
    this.callAdapterProvider = callAdapterProvider;
    assert converterProvider != null;
    this.converterProvider = converterProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRetrofit(
            okHttpClientProvider.get(),
            baseUrlProvider.get(),
            callAdapterProvider.get(),
            converterProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<CallAdapter.Factory> callAdapterProvider,
      Provider<Converter.Factory> converterProvider) {
    return new NetModule_ProvideRetrofitFactory(
        module, okHttpClientProvider, baseUrlProvider, callAdapterProvider, converterProvider);
  }
}
