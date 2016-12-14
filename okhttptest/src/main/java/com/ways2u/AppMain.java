package com.ways2u;

import com.google.gson.Gson;
import com.ways2u.bean.Meizi;
import com.ways2u.config.ConfigComponent;
import com.ways2u.config.ConfigModule;
import com.ways2u.config.DaggerConfigComponent;
import com.ways2u.net.DaggerNetComponent;
import com.ways2u.net.GankApi;
import com.ways2u.net.NetComponent;
import com.ways2u.net.NetModule;
import com.ways2u.reuse.Person;
import com.ways2u.reuse.ReuseModule;
import com.ways2u.user.Binder;
import com.ways2u.user.PrintPerson;
import com.ways2u.user.User;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by huanglong on 2016/12/12.
 */
public class AppMain {

    @Inject
    public GankApi gankApi;

    @Inject
    public User user;
    @Inject
    public Lazy<User> user1;
    @Inject
    public User user3;
    @Inject
    Binder binder;

    @Inject
    Person person;
    @Inject
    Person person1;

    @Inject
    Map<String, String> map;

    @Inject
    PrintPerson printPerson;

    //ConfigComponent的baseUrl 不能使用，依赖关系AppComponent-->NetComponent-->ConfigComponent
/*
    @Inject
    String baseUrl;
*/
    public AppMain() {

        ConfigComponent configComponent = DaggerConfigComponent.builder()
                .configModule(new ConfigModule()).reuseModule(new ReuseModule())
                .build();

        NetComponent netComponent = DaggerNetComponent.builder()
                .configComponent(configComponent)
                .netModule(new NetModule())
                .build();


        AppModule module = new AppModule();
        DaggerAppComponent.builder()
                .appModule(module)
                .netComponent(netComponent)
                .build()
                .inject(this);
    }


    public static void main(String args[]) {
        AppMain appMain = new AppMain();

        System.out.println(appMain.user);
        System.out.println(appMain.user1.get());
        System.out.println(appMain.user3);
        System.out.println(appMain.map);
        System.out.println(appMain.binder.getUser());

        System.out.println(appMain.person);
        System.out.println(appMain.person1);
        System.out.println(appMain.printPerson);


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

        System.out.println("OK");

        AppSub.main1(null);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
