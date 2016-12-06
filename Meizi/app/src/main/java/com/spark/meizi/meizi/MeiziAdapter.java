package com.spark.meizi.meizi;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.spark.meizi.R;
import com.spark.meizi.base.listener.BaseRecyclerAdapter;
import com.spark.meizi.meizi.detail.DetailActivity;
import com.spark.meizi.meizi.entity.Meizi;
import com.spark.meizi.utils.ActivityUtil;
import com.spark.meizi.utils.ImageLoader;
import com.spark.meizi.widget.ScaleImageView;

import java.util.List;


/**
 * Created by Spark on 8/2/2016.
 * Github: github/SparkYuan
 */
public class MeiziAdapter extends BaseRecyclerAdapter<Meizi.ResultsBean, MeiziAdapter.MeiziViewHolder> {

    @Override
    protected int getLayoutId() {
        return R.layout.meizi_rv_item;
    }

    @Override
    protected MeiziViewHolder createViewHolder(View view) {
        return new MeiziViewHolder(view,getData());
    }

    public static class MeiziViewHolder extends BaseRecyclerAdapter.BaseViewHolder<Meizi.ResultsBean> implements View.OnClickListener {

        private static final String TAG = "MeiziViewHolder";

        ScaleImageView itemImageView;
        private List<Meizi.ResultsBean> data;

        public MeiziViewHolder(View itemView, List<Meizi.ResultsBean> data) {
            super(itemView);
            this.data = data;

            itemImageView = (ScaleImageView)itemView.findViewById(R.id.iv_item);
            itemImageView.setOnClickListener(this);
        }

        @Override
        protected void bindData(Meizi.ResultsBean data) {
            itemImageView.setOriginalSize(data.getWidth(),data.getHeight());
            ImageLoader.loadImage(data.getUrl(),itemImageView,itemImageView.getContext());
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt(MeiziPresenter.INDEX,getAdapterPosition());
            bundle.putString(MeiziPresenter.MEIZIS,new Gson().toJson(data));
            ActivityUtil.startActivityNotInActivity(itemImageView.getContext(), DetailActivity.class,bundle);
        }
    }
}