package com.example.week02;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity_Explicit extends AppCompatActivity {
    public static final String TAG = "Week02_Demo_Activity_Lifecycle";
    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        closeButton = findViewById(R.id.buttonClose);
        closeButton.setOnClickListener(view -> finish());
    }
}