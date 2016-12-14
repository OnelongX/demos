package com.ways2u.net;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideRestAdapterFactory implements Factory<Retrofit> {
  private final NetModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<String> baseUrlProvider;

  private final Provider<Gson> gsonProvider;

  public NetModule_ProvideRestAdapterFactory(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<Gson> gsonProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
    assert baseUrlProvider != null;
    this.baseUrlProvider = baseUrlProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRestAdapter(
            okHttpClientProvider.get(), baseUrlProvider.get(), gsonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      NetModule module,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<String> baseUrlProvider,
      Provider<Gson> gsonProvider) {
    return new NetModule_ProvideRestAdapterFactory(
        module, okHttpClientProvider, baseUrlProvider, gsonProvider);
  }
}
