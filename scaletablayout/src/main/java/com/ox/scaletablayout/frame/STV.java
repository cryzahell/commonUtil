package com.ox.scaletablayout.frame;

import android.content.Context;
import android.util.AttributeSet;

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
}
