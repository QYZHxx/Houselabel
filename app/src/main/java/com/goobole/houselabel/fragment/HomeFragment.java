package com.goobole.houselabel.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.goobole.houselabel.MainActivity;
import com.goobole.houselabel.R;
import com.goobole.houselabel.bean.Banner;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener{

    private Toolbar mToolbar;
    private TextView image_scan;
    private List<Banner> mBanner;
    private SliderLayout mSliderLayout;
    private View view;
    private Handler handler;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        mToolbar = (Toolbar) view.findViewById(R.id.index_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了ToolBar", Toast.LENGTH_SHORT).show();
            }
        });
        initView();
        return view;
    }

    private void initView() {
        MainActivity mainActivity=(MainActivity)getActivity();
        handler=mainActivity.handler;
        image_scan=(TextView)mToolbar.findViewById(R.id.image_scan);
        mSliderLayout=(SliderLayout)view.findViewById(R.id.slider);
        image_scan.setOnClickListener(this);
        initDate();
        initSlider();
    }

    private void initDate() {
        mBanner=new ArrayList<>();
        for (int i=0;i<5;i++){
            Banner banner=new Banner();
            banner.setImgUrl("https://gss1.bdstatic.com/5eN1dDebRNRTm2_p8IuM_a/res/img/leshi20160502.png");
            banner.setName("广告:"+(i+1));
            mBanner.add(banner);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_scan:
                Toast.makeText(getContext(), "扫一扫", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessage(100);
                break;
        }
    }



    private void initSlider(){//初始化滑动广告
        if(mBanner !=null){
            for (Banner banner : mBanner){
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);
            }
        }
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//标签剧中显示
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);//平滑滑动
        mSliderLayout.setDuration(3000);//3秒滑动一次
    }

    @Override
    public void onResume() {
        mSliderLayout.startAutoCycle();
        super.onResume();
    }

    @Override
    public void onPause() {
        mSliderLayout.stopAutoCycle();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSliderLayout.stopAutoCycle();
    }
}
