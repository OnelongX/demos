package com.ways2u.net;

import com.google.gson.Gson;
import com.ways2u.config.ConfigModule;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by huanglong on 2016/12/12.
 */

@NetScope
@Component(modules = {NetModule.class, ConfigModule.class})
public interface NetComponent {
    OkHttpClient getOkHttpClient();
    Retrofit getRetrofit();
    Gson getGson();
    GankApi getGankApi();
    JsonService getRetrofitService();

    PostJsonService getPostJson();

}
