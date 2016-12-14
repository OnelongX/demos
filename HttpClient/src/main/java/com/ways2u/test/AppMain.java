package com.ways2u.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ways2u.config.ConfigModule;
import com.ways2u.net.*;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanglong on 2016/12/13.
 */
public class AppMain {
    @Inject
    public GankApi gankApi;
    @Inject
    JsonService retrofitService;
    @Inject
    PostJsonService postJsonService;
    @Inject
    Gson gson;

    public AppMain(){
        NetComponent netComponent = DaggerNetComponent.builder()
                .configModule(new ConfigModule())
                .netModule(new NetModule())
                .build();
        DaggerAppComponent.builder()
                .netComponent(netComponent)
                .appModule(new AppModule())
                .build()
                .inject(this);
    }


    public static void main(String[] agvs){
        AppMain appMain = new AppMain();

        Map<String,String> params = new HashMap<String, String>();
        params.put("o","1");
        params.put("token","2df0416ca60ac960443b9b815e71febf");
        params.put("re_order_no","R161202341509744");
        //Call<JsonObject> call = appMain.retrofitService.getData("data/福利/10/1",params);
        /*
        Call<JsonObject> call = appMain.retrofitService.postData("/json.do",params);
        call.enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonObject> call, Throwable throwable) {

            }
        });
*/
        String route =appMain.gson.toJson(params);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);

        //appMain.gson.toJsonTree(params)

        Call<JsonElement> call1 = appMain.postJsonService.postJson4(new Person());
        call1.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println("----"+response.body());
                System.out.println(Thread.currentThread().getId());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {

            }
        });
        /*
        Observable<Meizi> observable = appMain.gankApi.latest(10,1);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Meizi>() {
                    public void onSubscribe(Disposable disposable) {

                    }

                    public void onNext(Meizi meizi) {
                        System.out.println(new Gson().toJson(meizi));
                    }

                    public void onError(Throwable throwable) {

                    }

                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
*/
        System.out.println("OK");


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
