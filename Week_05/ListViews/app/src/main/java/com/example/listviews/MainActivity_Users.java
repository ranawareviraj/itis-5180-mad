package com.example.listviews;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_Users extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Bob Smith", 35));
        users.add(new User("Alice Brown", 23));
        users.add(new User("Bill Jordon", 27));
        users.add(new User("Tom Hank", 32));

        ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, users);
        listView.setAdapter(userArrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("ListView_Demo", "onItemClick, position: " + position + " and color: "
                    + userArrayAdapter.getItem(position).name);
        });
    }
}