package com.ox.scaletablayout.frame;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public abstract class STV extends android.support.v7.widget.AppCompatTextView implements TabSelectable {
    public STV(Context context) {
        this(context, null);
    }

    public STV(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public STV(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    public void onSelected(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTextColor(Color.WHITE);
            tv.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void unSelected(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTextColor(Color.GRAY);
            tv.setBackgroundColor(Color.WHITE);
        }
    }
}
