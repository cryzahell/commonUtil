package com.ox.simplecalender;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MonthLayout extends FrameLayout {

    public static final String TAG = MonthLayout.class.getName();
    /**
     *
     */
    Calendar calendar = Calendar.getInstance();


    public MonthLayout(Context context) {
        this(context, null);
    }

    public MonthLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MonthLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setMonth(Date month) {
        calendar.setTime(month);

//        cal.add(Calendar.MONTH, 1);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.add(Calendar.DAY_OF_MONTH, -1);
//        cal.setTime(cal.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());
        Log.e(TAG, "DAY_OF_MONTH" + (cal.get(Calendar.MONTH) + 1));//从0开始
        Log.e(TAG, "DAY_OF_MONTH" + cal.get(Calendar.DAY_OF_MONTH));
        Log.e(TAG, "DAY_OF_WEEK" + cal.get(Calendar.DAY_OF_WEEK));// 周日--周六 1--7
        Log.e(TAG, "DAY_OF_WEEK_IN_MONTH" + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        Log.e(TAG, "DAY_OF_YEAR" + cal.get(Calendar.DAY_OF_YEAR));
        Log.e(TAG, "WEEK_OF_MONTH" + cal.get(Calendar.WEEK_OF_MONTH));//第几周

        int curMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        int m;
        do {
            TextView tv1 = new TextView(getContext());
            tv1.setText("" + cal.get(Calendar.DAY_OF_MONTH));
            tv1.setTag(cal.get(Calendar.DAY_OF_WEEK));
            addView(tv1);
//            Log.e(TAG, "***************************" + tv1.getText().toString());
            cal.add(Calendar.DAY_OF_MONTH, 1);
            m = cal.get(Calendar.MONTH);
        } while (m == curMonth);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int avgWidth = (int) (getMeasuredWidth() * 1.0f / 7);
        int c = getChildCount();

        if (c <= 0) {
            return;
        }



        int left = (((int) getChildAt(0).getTag()) - 1) * avgWidth;
        int top = 0;
        for (int i = 0; i < c; i++) {
            View childAt = getChildAt(i);
            childAt.layout(left, top, left + avgWidth, top + childAt.getMeasuredHeight());
            left += avgWidth;
            if (left >= getMeasuredWidth()) {
                left = 0;
                top += childAt.getMeasuredHeight();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() <= 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        View parent = (View) getParent();
        int pw = parent.getMeasuredWidth();

        int avgWidth = (int) (pw * 1.0f / 7);
        int c = getChildCount();
        int left = (((int) getChildAt(0).getTag()) - 1) * avgWidth;
        int top = 0;
        for (int i = 0; i < c; i++) {
            View childAt = getChildAt(i);
            childAt.layout(left, top, left + avgWidth, top + childAt.getMeasuredHeight());
            left += avgWidth;
            if (left >= pw) {
                left = 0;
                top += childAt.getMeasuredHeight();
            }
        }

        if (left < pw) {
            top += getChildAt(0).getMeasuredHeight();
        }

        setMeasuredDimension(pw, top);
    }

    private void init(Context context) {

    }


}
