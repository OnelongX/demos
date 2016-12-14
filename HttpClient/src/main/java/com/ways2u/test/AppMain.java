package com.ways2u.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ways2u.config.ConfigModule;
import com.ways2u.net.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
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

    public AppMain() {
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

    public void testGetData() {
        Map<String, String> params = new HashMap<String, String>();
        Call<JsonElement> call = retrofitService.getData("person/2.do", params);
        call.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testGetDatas() {
        Map<String, String> params = new HashMap<String, String>();
        Call<JsonElement> call = retrofitService.getData("persons.do", params);
        call.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testPostData() { //not json error
        Map<String, String> params = new HashMap<String, String>();
        params.put("o", "1");
        Call<JsonElement> call = retrofitService.postData("json.do", params);
        call.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testGetMeizi() {
        Observable<Meizi> observable = gankApi.latest(10, 1);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Meizi>() {
                    public void onSubscribe(Disposable disposable) {

                    }

                    public void onNext(Meizi meizi) {
                        //new Gson().toJson(meizi)
                        System.out.println(meizi.getClass());
                    }

                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getLocalizedMessage());
                    }

                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

    public void testPostJson1() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("o", "1");
        params.put("token", "2df0416ca60ac960443b9b815e71febf");
        params.put("re_order_no", "R161202341509744");
        String route = gson.toJson(params);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        Call<JsonObject> call = postJsonService.postJson(body);
        call.enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testPostJson2() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("o", "1");
        params.put("token", "2df0416ca60ac960443b9b815e71febf");
        params.put("re_order_no", "R161202341509744");

        Call<JsonElement> call = postJsonService.postJson2(gson.toJsonTree(params));
        call.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }


    public void testPostJson3() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("o", "1");
        params.put("token", "2df0416ca60ac960443b9b815e71febf");
        params.put("re_order_no", "R161202341509744");

        Call<JsonElement> call = postJsonService.postJson3(params);
        call.enqueue(new Callback<JsonElement>() {
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<JsonElement> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }


    public void testPostJson4() {
        Call<Person> call = postJsonService.postJson4(new Person());
        call.enqueue(new Callback<Person>() {
            public void onResponse(Call<Person> call, Response<Person> response) {
                System.out.println(response.body());
            }

            public void onFailure(Call<Person> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testGetPersion() {
        Call<List<Person>> call = postJsonService.getPersons();
        call.enqueue(new Callback<List<Person>>() {
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                System.out.println(response.body().get(0).getClass());
            }

            public void onFailure(Call<List<Person>> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }

    public void testPostJson5() {
        MyRespone<Person> respone = new MyRespone<Person>();
        respone.header = new MyRespone.MyHeader();
        respone.header.code="101";
        respone.header.msg="ok";
        respone.body = new Person();

        Call<MyRespone<Person>> call = postJsonService.postJson5(respone);
        call.enqueue(new Callback<MyRespone<Person>>() {
            public void onResponse(Call<MyRespone<Person>> call, Response<MyRespone<Person>> response) {
                System.out.println(response.body().getClass());
                System.out.println(response.body().header.getClass());
                System.out.println(response.body().body.getClass());
                System.out.println(gson.toJson(response.body()));
            }

            public void onFailure(Call<MyRespone<Person>> call, Throwable throwable) {
                System.out.println(throwable.getLocalizedMessage());
            }
        });
    }


    public static void main(String[] agvs) {
        AppMain appMain = new AppMain();
        //appMain.testGetData();
        //appMain.testGetDatas();
        //appMain.testPostJson1();
        //appMain.testPostJson2();
        //appMain.testPostJson3();
        //appMain.testPostJson4();
        appMain.testGetPersion();
        //appMain.testPostJson5();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
