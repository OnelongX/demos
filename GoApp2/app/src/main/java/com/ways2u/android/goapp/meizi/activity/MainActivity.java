package com.ways2u.android.goapp.meizi.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ways2u.android.goapp.R;
import com.ways2u.android.goapp.base.BaseActivity;
import com.ways2u.android.goapp.meizi.fragment.MainFragment;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class MainActivity extends BaseActivity {
    private MainFragment mainFragment;
    private Handler handler;

    @Override
    public void handleMessage(Message msg) {
        e(msg.obj.toString());
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView() {
        mainFragment = new MainFragment();
        initFragment(mainFragment);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData(Bundle bundle) {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg =(Message)mainFragment.handler.obtainMessage(1,"Msg form Activity");
                mainFragment.handler.postMessage(msg);
            }
        },1000);
    }

    public void initFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (ft != null && fragment != null) {
            ft.add(R.id.main_content, fragment, "MainFragment");
            ft.commit();
        }
    }

}
