package com.ways2u.net;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class NetModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final NetModule module;

  private final Provider<Interceptor> loggingProvider;

  private final Provider<Interceptor> netInterceptorProvider;

  private final Provider<Boolean> isDebugProvider;

  private final Provider<Boolean> opnenNetworkInterceptorProvider;

  private final Provider<Long> timeOutProvider;

  public NetModule_ProvideOkHttpClientFactory(
      NetModule module,
      Provider<Interceptor> loggingProvider,
      Provider<Interceptor> netInterceptorProvider,
      Provider<Boolean> isDebugProvider,
      Provider<Boolean> opnenNetworkInterceptorProvider,
      Provider<Long> timeOutProvider) {
    assert module != null;
    this.module = module;
    assert loggingProvider != null;
    this.loggingProvider = loggingProvider;
    assert netInterceptorProvider != null;
    this.netInterceptorProvider = netInterceptorProvider;
    assert isDebugProvider != null;
    this.isDebugProvider = isDebugProvider;
    assert opnenNetworkInterceptorProvider != null;
    this.opnenNetworkInterceptorProvider = opnenNetworkInterceptorProvider;
    assert timeOutProvider != null;
    this.timeOutProvider = timeOutProvider;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideOkHttpClient(
            DoubleCheck.lazy(loggingProvider),
            DoubleCheck.lazy(netInterceptorProvider),
            isDebugProvider.get(),
            opnenNetworkInterceptorProvider.get(),
            timeOutProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(
      NetModule module,
      Provider<Interceptor> loggingProvider,
      Provider<Interceptor> netInterceptorProvider,
      Provider<Boolean> isDebugProvider,
      Provider<Boolean> opnenNetworkInterceptorProvider,
      Provider<Long> timeOutProvider) {
    return new NetModule_ProvideOkHttpClientFactory(
        module,
        loggingProvider,
        netInterceptorProvider,
        isDebugProvider,
        opnenNetworkInterceptorProvider,
        timeOutProvider);
  }
}
