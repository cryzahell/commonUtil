package com.ox.scaletablayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ox.scaletablayout.frame.STV;
import com.ox.scaletablayout.frame.ScaleTabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScaleTabLayout stl = (ScaleTabLayout) findViewById(R.id.stl);

        STV s0 = new STV(this) {
            @Override
            public int getItemId() {
                return 0;
            }
        };
        s0.setText("test0");
        s0.setBackgroundColor(Color.RED);
        s0.setAlpha(0.5f);
        stl.addItem(s0);

        STV s1 = new STV(this) {
            @Override
            public int getItemId() {
                return 1;
            }
        };
        s1.setText("hello world android");
        s1.setBackgroundColor(Color.BLUE);s1.setAlpha(0.5f);
        stl.addItem(s1);

        STV s2 = new STV(this) {
            @Override
            public int getItemId() {
                return 2;
            }
        };
        s2.setText("我很好");
        s2.setBackgroundColor(Color.GREEN);s2.setAlpha(0.5f);
        stl.addItem(s2);

    }
}
