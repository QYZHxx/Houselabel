package com.goobole.houselabel;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.goobole.houselabel.fragment.CartFragment;
import com.goobole.houselabel.fragment.HomeFragment;
import com.goobole.houselabel.fragment.MeFragment;
import com.goobole.houselabel.fragment.ReservationFragment;
import com.xys.libzxing.zxing.activity.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_index, tv_reservation, tv_shoppingcart, tv_me;
    private Fragment fragmentIndex, fragmentClassify, fragmentShopingCart, fragmentMe;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg!=null){
                switch (msg.what) {
                    case 100:
                        /**
                         * 接收到二维码来的指令,Activity执行替换操作
                         */
                        scan();
                        break;
                    case 99:
                        tv_reservation.performClick();//切换到商品预订
                        break;
                    default:
                        break;
                }
            }
        }

    };

    private void initView() {
        manager = getSupportFragmentManager();
        tv_index = (TextView) findViewById(R.id.main_tv_index);
        tv_reservation = (TextView) findViewById(R.id.main_tv_reservation);
        tv_shoppingcart = (TextView) findViewById(R.id.main_tv_shoppingcart);
        tv_me = (TextView) findViewById(R.id.main_tv_me);

        tv_index.setOnClickListener(this);
        tv_reservation.setOnClickListener(this);
        tv_shoppingcart.setOnClickListener(this);
        tv_me.setOnClickListener(this);

        tv_index.performClick();//模拟一次点击相当于点击了第一项
    }

    private void scan() {//二维码扫描
        Intent it = new Intent();
        it.setClass(this, CaptureActivity.class);
        this.startActivityForResult(it, 99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99 && resultCode==RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.get("result").toString();
            System.out.println(result);
            handler.sendEmptyMessage(99);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();//开启一个事物
        hideAllFragment(transaction);
        resetAllSelector();
        switch (v.getId()) {
            case R.id.main_tv_index:
                tv_index.setSelected(true);
                if (fragmentIndex == null) {
                    fragmentIndex = new HomeFragment();
                    transaction.add(R.id.main_frameLayout, fragmentIndex);
                } else {
                    transaction.show(fragmentIndex);
                }
                break;
            case R.id.main_tv_reservation:
                tv_reservation.setSelected(true);
                if (fragmentClassify == null) {
                    fragmentClassify = new ReservationFragment();
                    transaction.add(R.id.main_frameLayout, fragmentClassify);
                } else {
                    transaction.show(fragmentClassify);
                }
                break;
            case R.id.main_tv_shoppingcart:
                tv_shoppingcart.setSelected(true);
                if (fragmentShopingCart == null) {
                    fragmentShopingCart = new CartFragment();
                    transaction.add(R.id.main_frameLayout, fragmentShopingCart);
                } else {
                    transaction.show(fragmentShopingCart);
                }
                break;
            case R.id.main_tv_me:
                tv_me.setSelected(true);
                if (fragmentMe == null) {
                    fragmentMe = new MeFragment();
                    transaction.add(R.id.main_frameLayout, fragmentMe);
                } else {
                    transaction.show(fragmentMe);
                }
                break;
        }
        transaction.commit();//提交事物
    }

    /**
     * 隐藏所有Fragment
     *
     * @param transaction 碎片事物
     */
    public void hideAllFragment(FragmentTransaction transaction) {
        if (fragmentIndex != null) transaction.hide(fragmentIndex);
        if (fragmentClassify != null) transaction.hide(fragmentClassify);
        if (fragmentShopingCart != null) transaction.hide(fragmentShopingCart);
        if (fragmentMe != null) transaction.hide(fragmentMe);
    }

    /**
     * 重置所有选择器
     */
    public void resetAllSelector() {
        tv_index.setSelected(false);
        tv_reservation.setSelected(false);
        tv_shoppingcart.setSelected(false);
        tv_me.setSelected(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
