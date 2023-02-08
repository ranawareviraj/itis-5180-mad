package com.example.assessment2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_PROFILE = "KEY_PROFILE";
    Button buttonSetWeight, buttonSetGender, buttonSubmit, buttonReset;
    TextView textViewGender, textViewWeight, textViewYourName;
    public static final String TAG = "Assesment 2";
    String weight;
    String gender;

    ActivityResultLauncher<Intent> startForWeightResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result != null && result.getResultCode() == RESULT_OK) {
                                if (result.getData() != null && result.getData().getStringExtra(SetWeightActivity.WEIGHT) != null) {
                                    textViewWeight.setText(result.getData().getStringExtra(SetWeightActivity.WEIGHT).toString() + " lbs");
                                    try {
                                        weight = result.getData().getStringExtra(SetWeightActivity.WEIGHT).toString() + " lbs";
                                    } catch (NumberFormatException exception) {
                                        Toast.makeText(MainActivity.this, "select valid weight", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                textViewWeight.setText("");
                                weight = "";
                            }
                        }
                    });

    ActivityResultLauncher<Intent> startForGenderResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result != null && result.getResultCode() == RESULT_OK) {
                                if (result.getData() != null && result.getData().getStringExtra(SetGenderActivity.GENDER) != null) {
                                    // Do sth with Gender
                                    textViewGender.setText(result.getData().getStringExtra(SetGenderActivity.GENDER).toString());
                                    gender = result.getData().getStringExtra(SetGenderActivity.GENDER).toString();
                                }
                            } else if (result.getResultCode() == RESULT_CANCELED) {
//                                textViewGender.setText("Female");
                                gender = "";
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");

        textViewGender = findViewById(R.id.textViewGender);
        textViewWeight = findViewById(R.id.textViewWeight);
        textViewYourName = findViewById(R.id.textViewYourName);

        buttonSetWeight = findViewById(R.id.buttonSetWeight);
        buttonSetGender = findViewById(R.id.buttonSetGender);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonReset = findViewById(R.id.buttonReset);


        buttonSetWeight.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SetWeightActivity.class);
            startForWeightResult.launch(intent);
        });

        buttonSetGender.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SetGenderActivity.class);
            startForGenderResult.launch(intent);
        });

        buttonReset.setOnClickListener(v -> {
            textViewGender.setText("N/A");
            textViewWeight.setText("N/A");
            weight = "";
            gender = "";
        });

        buttonSubmit.setOnClickListener(v -> {
            String weightSelected = textViewWeight.getText().toString() + "";
            gender = textViewGender.getText().toString()+"";
            if (weightSelected.isEmpty() || weightSelected.equals("N/A")) {
                Toast.makeText(this, "Select weight", Toast.LENGTH_SHORT).show();
            }else if   (gender.isEmpty() || gender.equals("N/A")){
                Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            }else{
                Profile profile = new Profile(weightSelected, gender);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(KEY_PROFILE, profile);
                startActivity(intent);
            }
        });
    }
}
