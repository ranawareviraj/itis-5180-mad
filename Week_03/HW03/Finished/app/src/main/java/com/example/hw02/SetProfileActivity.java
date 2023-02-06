package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SetProfileActivity extends AppCompatActivity {
    public static final String PROFILE_KEY = "PROFILE_KEY";
    EditText editTextWeight;
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        editTextWeight = findViewById(R.id.editTextWeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);

        findViewById(R.id.buttonSetWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double weight = Double.parseDouble(editTextWeight.getText().toString());
                    String genderSelected = radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale ? "Male" : "Female";
                    Profile profile = new Profile(weight, genderSelected);
                    editTextWeight.setText("");
                    Intent intent = new Intent();
                    intent.putExtra(PROFILE_KEY, profile);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(SetProfileActivity.this, "Enter a valid number in weight", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}