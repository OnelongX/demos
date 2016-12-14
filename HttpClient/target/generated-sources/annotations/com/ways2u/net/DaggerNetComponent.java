package com.ways2u.net;

import com.google.gson.Gson;
import com.ways2u.config.ConfigModule;
import com.ways2u.config.ConfigModule_ProvideBaseUrlFactory;
import com.ways2u.config.ConfigModule_ProvideDebugFactory;
import com.ways2u.config.ConfigModule_ProvideOpenNetworkInterceptorFactory;
import com.ways2u.config.ConfigModule_TimeOutFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerNetComponent implements NetComponent {
  private Provider<Interceptor> provideInterceptorProvider;

  private Provider<Interceptor> NetworkInterceptorProvider;

  private Provider<Boolean> provideDebugProvider;

  private Provider<Boolean> provideOpenNetworkInterceptorProvider;

  private Provider<Long> timeOutProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<String> provideBaseUrlProvider;

  private Provider<CallAdapter.Factory> provideCallAdapterProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<Converter.Factory> provideConverterProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<GankApi> provideApiServiceProvider;

  private Provider<JsonService> provideJsonServiceProvider;

  private Provider<PostJsonService> providePostJsonServiceProvider;

  private DaggerNetComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static NetComponent create() {
    return builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideInterceptorProvider =
        DoubleCheck.provider(NetModule_ProvideInterceptorFactory.create(builder.netModule));

    this.NetworkInterceptorProvider =
        DoubleCheck.provider(NetModule_NetworkInterceptorFactory.create(builder.netModule));

    this.provideDebugProvider =
        SingleCheck.provider(ConfigModule_ProvideDebugFactory.create(builder.configModule));

    this.provideOpenNetworkInterceptorProvider =
        SingleCheck.provider(
            ConfigModule_ProvideOpenNetworkInterceptorFactory.create(builder.configModule));

    this.timeOutProvider =
        SingleCheck.provider(ConfigModule_TimeOutFactory.create(builder.configModule));

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(
            NetModule_ProvideOkHttpClientFactory.create(
                builder.netModule,
                provideInterceptorProvider,
                NetworkInterceptorProvider,
                provideDebugProvider,
                provideOpenNetworkInterceptorProvider,
                timeOutProvider));

    this.provideBaseUrlProvider =
        SingleCheck.provider(ConfigModule_ProvideBaseUrlFactory.create(builder.configModule));

    this.provideCallAdapterProvider =
        DoubleCheck.provider(NetModule_ProvideCallAdapterFactory.create(builder.netModule));

    this.provideGsonProvider =
        DoubleCheck.provider(NetModule_ProvideGsonFactory.create(builder.netModule));

    this.provideConverterProvider =
        DoubleCheck.provider(
            NetModule_ProvideConverterFactory.create(builder.netModule, provideGsonProvider));

    this.provideRetrofitProvider =
        DoubleCheck.provider(
            NetModule_ProvideRetrofitFactory.create(
                builder.netModule,
                provideOkHttpClientProvider,
                provideBaseUrlProvider,
                provideCallAdapterProvider,
                provideConverterProvider));

    this.provideApiServiceProvider =
        DoubleCheck.provider(
            NetModule_ProvideApiServiceFactory.create(builder.netModule, provideRetrofitProvider));

    this.provideJsonServiceProvider =
        DoubleCheck.provider(
            NetModule_ProvideJsonServiceFactory.create(
                builder.netModule,
                provideOkHttpClientProvider,
                provideBaseUrlProvider,
                provideConverterProvider));

    this.providePostJsonServiceProvider =
        DoubleCheck.provider(
            NetModule_ProvidePostJsonServiceFactory.create(
                builder.netModule,
                provideOkHttpClientProvider,
                provideBaseUrlProvider,
                provideConverterProvider));
  }

  @Override
  public OkHttpClient getOkHttpClient() {
    return provideOkHttpClientProvider.get();
  }

  @Override
  public Retrofit getRetrofit() {
    return provideRetrofitProvider.get();
  }

  @Override
  public Gson getGson() {
    return provideGsonProvider.get();
  }

  @Override
  public GankApi getGankApi() {
    return provideApiServiceProvider.get();
  }

  @Override
  public JsonService getRetrofitService() {
    return provideJsonServiceProvider.get();
  }

  @Override
  public PostJsonService getPostJson() {
    return providePostJsonServiceProvider.get();
  }

  public static final class Builder {
    private NetModule netModule;

    private ConfigModule configModule;

    private Builder() {}

    public NetComponent build() {
      if (netModule == null) {
        this.netModule = new NetModule();
      }
      if (configModule == null) {
        this.configModule = new ConfigModule();
      }
      return new DaggerNetComponent(this);
    }

    public Builder netModule(NetModule netModule) {
      this.netModule = Preconditions.checkNotNull(netModule);
      return this;
    }

    public Builder configModule(ConfigModule configModule) {
      this.configModule = Preconditions.checkNotNull(configModule);
      return this;
    }
  }
}
