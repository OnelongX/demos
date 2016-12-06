package com.spark.meizi;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

//import io.realm.Realm;
//import io.realm.RealmConfiguration;

/**
 * Created by SparkYuan on 12/13/2015.
 * Github: github.com/SparkYuan
 */

public class MeiziApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
/*
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build());
                */
        MeiziContext.getInstance().init(this);
    }

}
