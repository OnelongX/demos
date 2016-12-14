package com.ways2u.net;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module
public class NetModule {

    @Provides
    @NetScope
    public OkHttpClient provideOkHttpClient(boolean isDebug) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Provides
    @NetScope
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NetScope
    public Retrofit provideRestAdapter( OkHttpClient okHttpClient, String baseUrl, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    @Provides
    @NetScope
    public GankApi provideApiService(Retrofit restAdapter) {
        return restAdapter.create(GankApi.class);
    }


}
