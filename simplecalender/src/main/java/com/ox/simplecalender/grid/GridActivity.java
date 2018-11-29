package com.ox.simplecalender.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ox.simplecalender.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GridActivity extends AppCompatActivity {

    private NoScrollGridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        grid = findViewById(R.id.grid);

        final ArrayList<ItemDay> list = new ArrayList<>();
        Calendar time = Calendar.getInstance(Locale.CHINA);
        time.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < time.get(Calendar.DAY_OF_WEEK) - 1; i++) {
            list.add(new ItemDay());
        }

        int curMonth = time.get(Calendar.MONTH);
        int m;
        do {
            ItemDay item = new ItemDay();
            item.setTime(new Date(time.getTimeInMillis()));
            list.add(item);
            time.add(Calendar.DAY_OF_MONTH, 1);
            m = time.get(Calendar.MONTH);
        } while (m == curMonth);

        final DateFormat sdf = SimpleDateFormat.getDateInstance();
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemDay itemDay = list.get(position);
                Date t = itemDay.getTime();
                Toast.makeText(GridActivity.this, t == null ? "**" : sdf.format(t), Toast.LENGTH_SHORT).show();
            }
        });

        grid.setAdapter(new BaseAdapter() {
            Calendar cal = Calendar.getInstance(Locale.CHINA);

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public ItemDay getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                VH holder;
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_day, parent, false);
                    holder = new VH(convertView);
                    convertView.setTag(holder);
                }
                holder = (VH) convertView.getTag();

                ItemDay item = getItem(position);
                if (item.getTime() == null) {
                    holder.tv.setText("");
                } else {
                    cal.setTime(item.getTime());
                    holder.tv.setText("" + cal.get(Calendar.DAY_OF_MONTH));
                }
                return convertView;
            }
        });
    }

    public class VH {

        private final TextView tv;

        public VH(View itemView) {
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
