package com.goobole.houselabel.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


import com.goobole.houselabel.R;

/**
 * Created by Channe1 on 2016/6/6.
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context) {
        super(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.zdy_title, this);
    }
}
