package com.example.listviews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_Strings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        String[] colors = {"Red", "Green", "Blue"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, colors);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListView_Demo", "onItemClick, position: " + position + " and color: " + colors[position]);
            }
        });
    }
}