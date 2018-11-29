package com.ox.simplecalender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MonthLayout monthLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthLayout = findViewById(R.id.month);

        monthLayout.setMonth(new Date());

    }
}
