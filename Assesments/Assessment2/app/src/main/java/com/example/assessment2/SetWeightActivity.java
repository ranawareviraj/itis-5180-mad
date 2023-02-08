package com.example.assessment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetWeightActivity extends AppCompatActivity {

    public static final String WEIGHT = "WEIGHT";
    EditText editTextNumberDecimal;
    Button buttonCancel, buttonSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight);
        setTitle("Set Weight");

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonSet = findViewById(R.id.buttonSet);


        buttonSet.setOnClickListener(view -> {
            String weight = editTextNumberDecimal.getText().toString();
           if (weight.isEmpty() || weight == null){
               Toast.makeText(this, "Select valid weight", Toast.LENGTH_SHORT).show();
           }else{
               Intent intent = new Intent();
               intent.putExtra(WEIGHT, weight);
               setResult(RESULT_OK, intent);
               finish();
           }
        });

        buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}