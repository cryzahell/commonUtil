package com.ox.scaletablayout.frame;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TabView extends FrameLayout {
    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        TextView title = new TextView(context);
        addView(title);

        // TODO: 18-11-6 test
        title.setText("Hello");
    }
}
