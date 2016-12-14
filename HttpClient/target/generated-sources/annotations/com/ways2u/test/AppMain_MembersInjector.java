package com.ways2u.test;

import com.google.gson.Gson;
import com.ways2u.net.GankApi;
import com.ways2u.net.JsonService;
import com.ways2u.net.PostJsonService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppMain_MembersInjector implements MembersInjector<AppMain> {
  private final Provider<GankApi> gankApiProvider;

  private final Provider<JsonService> retrofitServiceProvider;

  private final Provider<PostJsonService> postJsonServiceProvider;

  private final Provider<Gson> gsonProvider;

  public AppMain_MembersInjector(
      Provider<GankApi> gankApiProvider,
      Provider<JsonService> retrofitServiceProvider,
      Provider<PostJsonService> postJsonServiceProvider,
      Provider<Gson> gsonProvider) {
    assert gankApiProvider != null;
    this.gankApiProvider = gankApiProvider;
    assert retrofitServiceProvider != null;
    this.retrofitServiceProvider = retrofitServiceProvider;
    assert postJsonServiceProvider != null;
    this.postJsonServiceProvider = postJsonServiceProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  public static MembersInjector<AppMain> create(
      Provider<GankApi> gankApiProvider,
      Provider<JsonService> retrofitServiceProvider,
      Provider<PostJsonService> postJsonServiceProvider,
      Provider<Gson> gsonProvider) {
    return new AppMain_MembersInjector(
        gankApiProvider, retrofitServiceProvider, postJsonServiceProvider, gsonProvider);
  }

  @Override
  public void injectMembers(AppMain instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.gankApi = gankApiProvider.get();
    instance.retrofitService = retrofitServiceProvider.get();
    instance.postJsonService = postJsonServiceProvider.get();
    instance.gson = gsonProvider.get();
  }

  public static void injectRetrofitService(
      AppMain instance, Provider<JsonService> retrofitServiceProvider) {
    instance.retrofitService = retrofitServiceProvider.get();
  }

  public static void injectPostJsonService(
      AppMain instance, Provider<PostJsonService> postJsonServiceProvider) {
    instance.postJsonService = postJsonServiceProvider.get();
  }

  public static void injectGson(AppMain instance, Provider<Gson> gsonProvider) {
    instance.gson = gsonProvider.get();
  }
}
