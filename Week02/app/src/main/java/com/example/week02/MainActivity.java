package com.example.week02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Week02_Demo_Intent_Explicit";
    Button buttonGoToSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToSecond = findViewById(R.id.buttonGoToSecond);

        buttonGoToSecond.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity_Explicit.class);
            startActivity(intent);
        });
    }
}