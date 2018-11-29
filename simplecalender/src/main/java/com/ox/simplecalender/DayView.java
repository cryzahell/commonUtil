package com.ox.simplecalender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import java.util.Date;

public class DayView extends android.support.v7.widget.AppCompatTextView {

    private TextView tv;
    private Date time;
    private int week;


    public DayView(Context context) {
        this(context, null);
    }

    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * init
     */
    private void init(Context context) {
//        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        tv = new TextView(context);
//        tv.setIncludeFontPadding(false);
//        tv.setGravity(Gravity.CENTER);
//        tv.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        addView(tv);
        setGravity(Gravity.CENTER);
        setIncludeFontPadding(false);
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int tvl = (int) ((getMeasuredWidth() - tv.getMeasuredWidth()) * 1.0f / 2);
//        tv.layout(tvl, 0, tvl + tv.getMeasuredWidth(), tv.getMeasuredHeight());
//    }

//    public TextView getTv() {
//        return tv;
//    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
