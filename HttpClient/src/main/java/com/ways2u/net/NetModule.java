package com.ways2u.net;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import java.util.concurrent.TimeUnit;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module
public class NetModule {

    @Provides
    @NetScope
    @Named("Interceptor")
    public Interceptor provideInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @NetScope
    @Named("NetworkInterceptor")
    public Interceptor NetworkInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @NetScope
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NetScope
    public CallAdapter.Factory provideCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @NetScope
    public Converter.Factory provideConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @NetScope
    public OkHttpClient provideOkHttpClient(@Named("Interceptor") Lazy<Interceptor> logging,
                                            @Named("NetworkInterceptor") Lazy<Interceptor> netInterceptor,
                                            @Named("debug") boolean isDebug,
                                            @Named("networkInterceptor") boolean opnenNetworkInterceptor,
                                            long timeOut) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isDebug) {
            builder.addInterceptor(logging.get());
        }
        if (opnenNetworkInterceptor) {
            builder.addNetworkInterceptor(netInterceptor.get());
        }



        builder.connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS);
        return builder.build();
    }


    @Provides
    @NetScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,
                                    String baseUrl,
                                    CallAdapter.Factory callAdapter,
                                    Converter.Factory converter) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(converter);
        return builder.build();
    }

    @Provides
    @NetScope
    public GankApi provideApiService(Retrofit restAdapter) {
        return restAdapter.create(GankApi.class);
    }

    @Provides
    @NetScope
    public JsonService provideJsonService(OkHttpClient okHttpClient,
                                              String baseUrl,
                                              Converter.Factory converter){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                //.addCallAdapterFactory(callAdapter)
                .addConverterFactory(converter);
        return builder.build().create(JsonService.class);
    }

    @Provides
    @NetScope
    public PostJsonService providePostJsonService(OkHttpClient okHttpClient,
                                              String baseUrl,
                                              Converter.Factory converter){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(baseUrl)
                //.addCallAdapterFactory(callAdapter)
                .addConverterFactory(converter);
        return builder.build().create(PostJsonService.class);
    }


}
