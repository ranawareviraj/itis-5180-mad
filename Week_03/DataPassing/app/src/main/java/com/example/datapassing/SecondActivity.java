package com.example.datapassing;

import static com.example.datapassing.MainActivity.NAME_KEY;
import static com.example.datapassing.MainActivity.USERS_KEY;
import static com.example.datapassing.MainActivity.USER_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Button buttonClose;
    TextView textViewGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("SecondActivity");
        buttonClose = findViewById(R.id.buttonClose);
        textViewGreeting = findViewById(R.id.textViewGreeting);

        // Retrieving String data
       /* if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(NAME_KEY)){
            String name = getIntent().getStringExtra(NAME_KEY);
            textViewGreeting.setText("Hello " + name + " !");
            Log.d(MainActivity.TAG, "onCreate: Name retrieved");
        }*/

        // Retrieving Object data
       /* if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(NAME_KEY)){
            User user = (User) getIntent().getSerializableExtra(USER_KEY);
            String name = user.name;
            textViewGreeting.setText("Hello " + name + " !");
            Log.d(MainActivity.TAG, "onCreate: User retrieved");
        }*/

        // Retrieving List<Object>
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(USERS_KEY)){
            List<User> users = (List<User>) getIntent().getSerializableExtra(USERS_KEY);
            Collections.shuffle(users);
            User user = users.get(0);
            String name = user.name;
            textViewGreeting.setText("Hello " + name + " !");
            Log.d(MainActivity.TAG, "onCreate: User retrieved");
        }

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.TAG, "onClick: ");
                finish();
            }
        });
    }
}