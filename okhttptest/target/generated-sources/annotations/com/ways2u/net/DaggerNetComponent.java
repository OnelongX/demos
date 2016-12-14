package com.ways2u.net;

import com.google.gson.Gson;
import com.ways2u.config.ConfigComponent;
import dagger.internal.DoubleCheck;
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
public final class DaggerNetComponent implements NetComponent {
  private Provider<Boolean> isDebugProvider;

  private Provider<OkHttpClient> provideOkHttpClientProvider;

  private Provider<String> getBaseUrlProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<Retrofit> provideRestAdapterProvider;

  private Provider<GankApi> provideApiServiceProvider;

  private DaggerNetComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.isDebugProvider =
        new Factory<Boolean>() {
          private final ConfigComponent configComponent = builder.configComponent;

          @Override
          public Boolean get() {
            return Preconditions.checkNotNull(
                configComponent.isDebug(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.provideOkHttpClientProvider =
        DoubleCheck.provider(
            NetModule_ProvideOkHttpClientFactory.create(builder.netModule, isDebugProvider));

    this.getBaseUrlProvider =
        new Factory<String>() {
          private final ConfigComponent configComponent = builder.configComponent;

          @Override
          public String get() {
            return Preconditions.checkNotNull(
                configComponent.getBaseUrl(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.provideGsonProvider =
        DoubleCheck.provider(NetModule_ProvideGsonFactory.create(builder.netModule));

    this.provideRestAdapterProvider =
        DoubleCheck.provider(
            NetModule_ProvideRestAdapterFactory.create(
                builder.netModule,
                provideOkHttpClientProvider,
                getBaseUrlProvider,
                provideGsonProvider));

    this.provideApiServiceProvider =
        DoubleCheck.provider(
            NetModule_ProvideApiServiceFactory.create(
                builder.netModule, provideRestAdapterProvider));
  }

  @Override
  public GankApi getGankApi() {
    return provideApiServiceProvider.get();
  }

  public static final class Builder {
    private NetModule netModule;

    private ConfigComponent configComponent;

    private Builder() {}

    public NetComponent build() {
      if (netModule == null) {
        this.netModule = new NetModule();
      }
      if (configComponent == null) {
        throw new IllegalStateException(ConfigComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerNetComponent(this);
    }

    public Builder netModule(NetModule netModule) {
      this.netModule = Preconditions.checkNotNull(netModule);
      return this;
    }

    public Builder configComponent(ConfigComponent configComponent) {
      this.configComponent = Preconditions.checkNotNull(configComponent);
      return this;
    }
  }
}
