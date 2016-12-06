package com.spark.meizi.home;


import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.spark.meizi.R;
import com.spark.meizi.about.AboutActivity;
import com.spark.meizi.base.BaseActivity;
import com.spark.meizi.base.BaseFragment;
import com.spark.meizi.base.BaseFragmentPagerAdapter;
import com.spark.meizi.utils.ActivityUtil;


public class HomeActivity extends BaseActivity<HomePresenter> implements IHome {


    ViewPager viewPager;

    Toolbar toolbar;
    BaseFragmentPagerAdapter<BaseFragment> adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initSubViews(View view) {
        super.initSubViews(view);
        viewPager = (ViewPager)findViewById(R.id.vp_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new BaseFragmentPagerAdapter<>(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.initAdapterData(adapter);
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            ActivityUtil.startActivity(this, AboutActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

