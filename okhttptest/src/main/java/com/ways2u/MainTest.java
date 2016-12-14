package com.ways2u;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ways2u.bean.Meizi;
import com.ways2u.net.GankApi;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by huanglong on 2016/10/18.
 */
public class MainTest {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public static void main1(String args[]) throws IOException {


        Observable<String> myObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        System.out.println("1 " + Thread.currentThread());
                        observableEmitter.onNext("Hello world!");
                        observableEmitter.onComplete();
                    }
                }
        );

        //ExecutorService service = Executors.newSingleThreadExecutor();
        //Scheduler scheduler     = Schedulers.from(service);
        myObservable = myObservable.observeOn(Schedulers.newThread());
        myObservable = myObservable.subscribeOn(Schedulers.io());

        myObservable.subscribe(new Observer<String>() {
            public void onSubscribe(Disposable disposable) {

            }

            public void onNext(String s) {
                System.out.println("2 " + Thread.currentThread());
            }

            public void onError(Throwable throwable) {

            }

            public void onComplete() {
                System.out.println("3 " + Thread.currentThread());
            }
        });




/*

        Disposable disposable = Observable.empty()
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    //@Override
                    public void accept(Object integer) throws Exception {
                        //这里接收数据项
                        System.out.println("5 " + Thread.currentThread().getId());
                    }
                }, new Consumer<Throwable>() {
                    //@Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                    }
                }, new Action() {
                    //@Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        System.out.println("6 " + Thread.currentThread().getId());
                    }
                });


*/

        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                System.out.println("21 " + Thread.currentThread());
                flowableEmitter.onNext("fffffff");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());


        flowable.subscribe(new Subscriber<String>() {
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            public void onNext(String s) {
                System.out.println("23 " + Thread.currentThread());
            }

            public void onError(Throwable throwable) {
                System.out.println("24 " + Thread.currentThread());
            }

            public void onComplete() {
                System.out.println("25 " + Thread.currentThread());
            }
        });


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        builder.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        GankApi gankApi = retrofit.create(GankApi.class);

        Observable<Meizi> observable = gankApi.latest(10, 1);
        observable.subscribe(new Observer<Meizi>() {
            public void onSubscribe(Disposable disposable) {

            }

            public void onNext(Meizi meizi) {
                System.out.println(new Gson().toJson(meizi));
            }

            public void onError(Throwable throwable) {

            }

            public void onComplete() {

            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/*
        MainTest m= new MainTest();

        //String res = m.run("http://127.0.0.1:8888");
        //System.out.println(res);
        String res = m.post("http://127.0.0.1:8888/json","{\"test\":\"ttt\"}");
        System.out.println(res);
        res = m.post("http://127.0.0.1:8888/json1","{\"test\":\"ttt2\"}");
        System.out.println(res);
*/
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
