package com.ox.scaletablayout.frame;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class ScaleTabLayout extends FrameLayout implements Animator.AnimatorListener {
    private View lineView;
    private boolean isLineMoving = false;
    private View lastSelectedView = null;

    private OnTabSelectChangeListener onTabSelectedListener;

    public OnTabSelectChangeListener getOnTabSelectedListener() {
        return onTabSelectedListener;
    }

    /**
     * tab 切换 监听
     */
    public void setOnTabSelectedListener(OnTabSelectChangeListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

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
        lineView.setLayoutParams(new LayoutParams(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics())));
        lineView.setBackgroundColor(Color.parseColor("#35bab0"));
        addView(lineView);
    }

    /**
     * 添加item
     *
     * @param tabSelectable 实现{@link TabSelectable}的{@link View}的子类
     * @return this
     */
    public ScaleTabLayout addItem(final TabSelectable tabSelectable) {
        if (tabSelectable instanceof View) {
            ((View) tabSelectable).setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            addView((View) tabSelectable);
            ((View) tabSelectable).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLineMoving) {
                        onItemClick(tabSelectable.getItemId(), tabSelectable);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 通过id 选中某个item
     *
     * @param id {@link TabSelectable#getItemId()}
     */
    public void selectItem(int id) {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TabSelectable) {
                if (((TabSelectable) v).getItemId() == id) {
                    v.callOnClick();
                    break;
                }
            }
        }
    }

    private void onItemClick(int id, TabSelectable tabSelectable) {
        int lineLeft = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TabSelectable) {
                TabSelectable ts = (TabSelectable) v;
                if (ts.getItemId() == id) {
                    //选中 和 非选中样式变化
                    //unselected
                    if (lastSelectedView != null && lastSelectedView instanceof TabSelectable) {
                        TabSelectable lts = (TabSelectable) this.lastSelectedView;
                        lts.unSelected(this.lastSelectedView);
                        if (onTabSelectedListener != null) {
                            onTabSelectedListener.onTabUnSelected(lts.getItemId(), lastSelectedView);
                        }
                    }
                    //selected
                    tabSelectable.onSelected(v);
                    if (onTabSelectedListener != null) {
                        onTabSelectedListener.onTabSelected(id, v);
                    }
                    lastSelectedView = v;

                    ValueAnimator ot = ValueAnimator.ofInt(lineView.getLeft(), v.getLeft());
//                    ot.setInterpolator(new DecelerateInterpolator());
                    ot.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int val = (int) animation.getAnimatedValue();
                            lineView.setLeft(val);
                        }
                    });

                    ValueAnimator ow = ValueAnimator.ofInt(lineView.getMeasuredWidth(), v.getMeasuredWidth());
//                    ow.setInterpolator(new DecelerateInterpolator());
                    ow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            ViewGroup.LayoutParams lp = lineView.getLayoutParams();
                            int val = (int) animation.getAnimatedValue();
                            lp.width = val;
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
        isLineMoving = true;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isLineMoving = false;
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
