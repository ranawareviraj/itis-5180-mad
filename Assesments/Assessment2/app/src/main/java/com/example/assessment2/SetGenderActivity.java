package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SetGenderActivity extends AppCompatActivity {
    public static final String GENDER = "GENDER";
    Button buttonCancel, buttonSet;
    RadioGroup radioGroupGender;
    RadioButton radioButtonFemale, radioButtonMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gender);
        setTitle("Set Gender");

        buttonSet = findViewById(R.id.buttonSet);
        buttonCancel = findViewById(R.id.buttonCancel);

        radioGroupGender = findViewById(R.id.radioGroupGender);


        buttonSet.setOnClickListener(view -> {
            String gender = "Female";
            int checkedRadioButtonId = radioGroupGender.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radioButtonMale) {
                gender = "Male";
            }
            Intent intent = new Intent();
            intent.putExtra(GENDER, gender);
            setResult(RESULT_OK, intent);
            finish();
        });

        buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
