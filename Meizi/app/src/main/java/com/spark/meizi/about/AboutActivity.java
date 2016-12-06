package com.spark.meizi.about;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.spark.meizi.R;
import com.spark.meizi.base.BaseActivity;


public class AboutActivity extends BaseActivity {

    Toolbar tbAbout;

    @Override
    public void initSubViews(View view) {
        super.initSubViews(view);
        tbAbout =  (Toolbar)findViewById(R.id.tb_about);
        setSupportActionBar(tbAbout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
