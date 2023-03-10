package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
            User user = users.remove(position);
            userArrayAdapter.notifyDataSetChanged();
            Log.d("ListView", "Removed used " + users.get(position));
        });
    }
}