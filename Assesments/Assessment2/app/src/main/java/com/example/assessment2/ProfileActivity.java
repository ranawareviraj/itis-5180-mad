package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewGender, textViewWeight, textViewYourName;
    Button buttonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        textViewGender = findViewById(R.id.textViewGender);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewYourName = findViewById(R.id.textViewYourName);
        buttonClose = findViewById(R.id.buttonClose);

        Profile profile;
        if (getIntent() != null && getIntent().hasExtra(MainActivity.KEY_PROFILE)) {
          profile = (Profile) getIntent().getSerializableExtra(MainActivity.KEY_PROFILE);
            textViewGender.setText(profile.getGender());
            textViewWeight.setText(profile.getWeight() + "");
            textViewYourName.setText("Viraj Bhalchandra Ranaware");
        }

        buttonClose.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}