package com.goobole.houselabel.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.goobole.houselabel.R;


public class MeFragment extends Fragment implements View.OnClickListener {

    private LinearLayout me_login;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        initView();
        return view;
    }

    private void initView() {
        me_login=(LinearLayout)view.findViewById(R.id.me_login);
        me_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_login:
                Toast.makeText(getContext(), "点击了登陆", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
