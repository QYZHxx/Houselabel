package com.goobole.houselabel.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.goobole.houselabel.R;


public class ReservationFragment extends Fragment {


    private LinearLayout shop_background;
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reservation, null);
        initView();
        return view;
    }

    int a=0;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                switch (msg.what) {
                    case 1:
                        shop_background.setBackgroundResource(image[a]);
                        a++;
                        if (a==7){
                            a=0;
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    };

    int[] image;

    private void initView() {
        image = new int[]{R.drawable.shop_background, R.drawable.shop_background1, R.drawable.shop_background2, R.drawable.shop_background3, R.drawable.shop_background4, R.drawable.shop_background5, R.drawable.shop_background6};
        shop_background = (LinearLayout) view.findViewById(R.id.shop_background);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(3000);
                        handler.sendEmptyMessage(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
