package com.ox.scaletablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ox.scaletablayout.frame.STV;
import com.ox.scaletablayout.frame.ScaleTabLayout;

public class MainActivity extends AppCompatActivity {

    private ScaleTabLayout stl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stl = (ScaleTabLayout) findViewById(R.id.stl);

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
        s1.setBackgroundColor(Color.BLUE);
        s1.setAlpha(0.5f);
        stl.addItem(s1);

        STV s2 = new STV(this) {
            @Override
            public int getItemId() {
                return 2;
            }
        };
        s2.setText("我很好");
        s2.setBackgroundColor(Color.GREEN);
        s2.setAlpha(0.5f);
        stl.addItem(s2);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stl.selectItem(0);
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stl.selectItem(1);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stl.selectItem(2);
            }
        });

    }

    private void addItem() {
        final int id = (int) (Math.random() * 22222);
        STV s2 = new STV(this) {
            @Override
            public int getItemId() {
                return id;
            }
        };
        s2.setText("我很好" + id);
        s2.setBackgroundColor(Color.argb(255,
                ((int) (Math.random() * 255)),
                ((int) (Math.random() * 255)),
                ((int) (Math.random() * 255))
        ));
        s2.setAlpha(0.5f);
        stl.addItem(s2);
    }

}
