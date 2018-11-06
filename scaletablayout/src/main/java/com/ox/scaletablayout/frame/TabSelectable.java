package com.ox.scaletablayout.frame;

import android.view.View;

public interface TabSelectable {
    int getItemId();

    void onSelected(View view);

    void unSelected(View view);
}
