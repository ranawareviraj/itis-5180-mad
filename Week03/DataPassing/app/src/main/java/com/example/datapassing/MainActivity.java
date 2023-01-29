package com.example.datapassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Data Passing";
    public static final String NAME_KEY = "NAME";
    public static final String USER_KEY = "NAME";
    public static final String USERS_KEY = "NAME";
    Button buttonMoveToSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");

        buttonMoveToSecond = findViewById(R.id.buttonMoveToSecond);
        buttonMoveToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.datapassing.SecondActivity.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                // Pass String
//                intent.putExtra(NAME_KEY, "Viru");

                // Pass Object
//                intent.putExtra(USER_KEY, new User("Alice", 22));

                //Pass List
                ArrayList<User> users = new ArrayList<>();
                users.add(new User("Alice", 22));
                users.add(new User("Bob", 24));
                intent.putExtra(USERS_KEY, users);

                // Switch to Activity specified
                startActivity(intent);
            }
        });
    }
}