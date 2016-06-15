package com.goobole.houselabel.widgets;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goobole.houselabel.R;

/**
 * Created by Channe1 on 2016/6/14.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {

    private ImageView img_return;
    private TextView title_name;
    private TextView title_zc;
    private Context context;
    private Activity activity;

    public TitleLayout(Context context) {
        super(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.custom_titlet, this);
        title_name = (TextView) findViewById(R.id.title_name);
        title_zc = (TextView) findViewById(R.id.title_zc);
        img_return = (ImageView) findViewById(R.id.img_return);
        img_return.setOnClickListener(this);
    }

    public void setIsHidderTitle_zc(boolean hidden) {
        if (hidden) {
            title_zc.setVisibility(View.INVISIBLE);
        }
    }

    public void setTitle(String str) {
        title_name.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_return:
                activity.finish();
                break;
        }
    }
}
