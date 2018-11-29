package com.ox.simplecalender.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ox.simplecalender.R;
import com.ox.simplecalender.layout.MonthLayout;

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
