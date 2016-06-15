package com.goobole.houselabel.ui;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.goobole.houselabel.R;
import com.goobole.houselabel.ui.base.BaseActivity;
import com.goobole.houselabel.widgets.TitleLayout;


public class LoginActivity extends BaseActivity implements OnClickListener {

    private TitleLayout custom_title;
    private LinearLayout login_quick, login_account,login_hide_quick,login_hide_account;
    private EditText edit_phone;
    private Button btn_verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {

        custom_title = (TitleLayout) findViewById(R.id.custom_title);
        login_quick = (LinearLayout) findViewById(R.id.login_quick);
        login_account = (LinearLayout) findViewById(R.id.login_account);
        login_hide_quick = (LinearLayout) findViewById(R.id.login_hide_quick);
        login_hide_account = (LinearLayout) findViewById(R.id.login_hide_account);
        edit_phone = (EditText) findViewById(R.id.login_edit_phone);
        btn_verification = (Button) findViewById(R.id.login_btn_verification);
    }

    @Override
    protected void initView() {
        custom_title.setTitle("登陆");
        custom_title.setIsHidderTitle_zc(false);
        login_quick.setOnClickListener(this);
        login_quick.setBackgroundResource(R.color.white);
        login_account.setOnClickListener(this);
        btn_verification.setOnClickListener(this);
        login_hide_account.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //快捷登陆
            case R.id.login_quick:
                login_quick.setBackgroundResource(R.color.white);
                login_account.setBackgroundResource(R.color.gray1);
                login_hide_quick.setVisibility(View.VISIBLE);
                login_hide_account.setVisibility(View.GONE);
                break;
            //账号登陆
            case R.id.login_account:
                login_account.setBackgroundResource(R.color.white);
                login_quick.setBackgroundResource(R.color.gray1);
                login_hide_quick.setVisibility(View.GONE);
                login_hide_account.setVisibility(View.VISIBLE);
                break;
            //获取验证码
            case R.id.login_btn_verification:
                System.out.println("获取验证码");
                btn_verification.setClickable(false);
                btn_verification.setBackgroundResource(R.color.gray);
                break;
            default:
                break;
        }

    }

}
