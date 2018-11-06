package com.ox.scaletablayout.frame;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ox.commonlibrary.Logger;

public class ScaleTabLayout extends FrameLayout implements Animator.AnimatorListener {
    private View lineView;
    private boolean isPlaying = false;

    public ScaleTabLayout(Context context) {
        this(context, null);
    }

    public ScaleTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        lineView = new View(context);
        lineView.setLayoutParams(new LayoutParams(60, 30));
        lineView.setBackgroundColor(Color.parseColor("#35bab0"));
        addView(lineView);
    }

    public ScaleTabLayout addItem(final TabSelectable tabSelectable) {
        if (tabSelectable instanceof View) {
            ((View) tabSelectable).setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            addView((View) tabSelectable);
            ((View) tabSelectable).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPlaying) {
                        onItemClick(tabSelectable.getItemId());
                    }
                }
            });
        }
        return this;
    }

    private void onItemClick(int id) {
        int lineLeft = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TabSelectable) {
                TabSelectable ts = (TabSelectable) v;
                if (ts.getItemId() == id) {

                    Logger.sout("" + lineView.getLeft() + "," + v.getLeft());
                    ValueAnimator ot = ValueAnimator.ofInt(lineView.getLeft(), v.getLeft());
                    ot.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int val = (int) animation.getAnimatedValue();
                            lineView.setLeft(val);
                            Logger.sout("aaa = " + val);
                            invalidate();
                        }
                    });

                    Logger.sout("" + lineView.getMeasuredWidth() + "," + v.getMeasuredWidth());
                    ValueAnimator ow = ValueAnimator.ofInt(lineView.getMeasuredWidth(), v.getMeasuredWidth());
                    ow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            ViewGroup.LayoutParams lp = lineView.getLayoutParams();
                            int val = (int) animation.getAnimatedValue();
                            lp.width = val;
                            Logger.sout("bbb = " + val);
                            requestLayout();
                        }
                    });

                    AnimatorSet as = new AnimatorSet();
                    as.setDuration(300);
                    as.playTogether(ot, ow);
                    as.addListener(this);
                    as.start();
                    break;
                }
                lineLeft += v.getMeasuredWidth();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int mh = 0;
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View c = getChildAt(i);
            if (c instanceof TabSelectable) {
                mh = Math.max(mh, c.getMeasuredHeight());
                c.layout(left, 0, left + c.getMeasuredWidth(), c.getMeasuredHeight());
                left += c.getMeasuredWidth();
            }
        }


        lineView.layout(lineView.getLeft(), mh, lineView.getLeft() + lineView.getMeasuredWidth(), mh + lineView.getMeasuredHeight());
        Logger.sout("l " + lineView.getLeft() + " t " + mh + " r " + (lineView.getLeft() + lineView.getMeasuredWidth()) + " b " + (mh + lineView.getMeasuredHeight()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int w = 0;
        int h = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View c = getChildAt(i);
            if (c instanceof TabSelectable) {
                w += c.getMeasuredWidth();
                h = Math.max(h, c.getMeasuredHeight());
            }
        }
        h += lineView.getMeasuredHeight();
        setMeasuredDimension(w, h);
    }

    @Override
    public void onAnimationStart(Animator animation) {
        isPlaying = true;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isPlaying = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}