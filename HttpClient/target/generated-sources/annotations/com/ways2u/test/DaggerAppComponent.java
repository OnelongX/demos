package com.ways2u.test;

import com.google.gson.Gson;
import com.ways2u.net.GankApi;
import com.ways2u.net.JsonService;
import com.ways2u.net.NetComponent;
import com.ways2u.net.PostJsonService;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<AppContext> provideAppContextProvider;

  private Provider<GankApi> getGankApiProvider;

  private Provider<JsonService> getRetrofitServiceProvider;

  private Provider<PostJsonService> getPostJsonProvider;

  private Provider<Gson> getGsonProvider;

  private MembersInjector<AppMain> appMainMembersInjector;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideAppContextProvider =
        DoubleCheck.provider(AppModule_ProvideAppContextFactory.create(builder.appModule));

    this.getGankApiProvider =
        new Factory<GankApi>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public GankApi get() {
            return Preconditions.checkNotNull(
                netComponent.getGankApi(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.getRetrofitServiceProvider =
        new Factory<JsonService>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public JsonService get() {
            return Preconditions.checkNotNull(
                netComponent.getRetrofitService(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.getPostJsonProvider =
        new Factory<PostJsonService>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public PostJsonService get() {
            return Preconditions.checkNotNull(
                netComponent.getPostJson(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.getGsonProvider =
        new Factory<Gson>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public Gson get() {
            return Preconditions.checkNotNull(
                netComponent.getGson(), "Cannot return null from a non-@Nullable component method");
          }
        };

    this.appMainMembersInjector =
        AppMain_MembersInjector.create(
            getGankApiProvider, getRetrofitServiceProvider, getPostJsonProvider, getGsonProvider);
  }

  @Override
  public AppContext getContext() {
    return provideAppContextProvider.get();
  }

  @Override
  public void inject(AppMain appMain) {
    appMainMembersInjector.injectMembers(appMain);
  }

  public static final class Builder {
    private AppModule appModule;

    private NetComponent netComponent;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      if (netComponent == null) {
        throw new IllegalStateException(NetComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder netComponent(NetComponent netComponent) {
      this.netComponent = Preconditions.checkNotNull(netComponent);
      return this;
    }
  }
}
