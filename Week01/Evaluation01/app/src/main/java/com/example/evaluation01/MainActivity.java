package com.example.evaluation01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText enterNumberA, enterNumberB;
    TextView result, textViewDivide;
    //    RadioButton calculate, reset;
    Button calculate, reset;
    double discount = 0.0;
    int checkedId;

    public static String TAG = "Eval01";


 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterNumberA = findViewById(R.id.editTextNumberA);
        enterNumberB = findViewById(R.id.editTextNumberB);
        result = findViewById(R.id.textViewResult);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        calculate = findViewById(R.id.radioButtonCalculate);
        reset = findViewById(R.id.radioButtonReset);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonCalculate) {
                    try {
                        double numberA = Double.parseDouble(enterNumberA.getText().toString());
                        Log.d(TAG, "numberA " + numberA);
                        double numberB = Double.parseDouble(enterNumberB.getText().toString());
                        Log.d(TAG, "numberB " + numberB);
                        if (numberB == 0) {
                            Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        } else {
                            double discount = 1.0 * numberA / (numberB);
                            Log.d(TAG, "onCreate: " + discount);
                            result.setText(String.format("%.2f", discount));
                        }
                    } catch (NumberFormatException exception) {
                        Toast.makeText(MainActivity.this, "Enter both A and B to calculate discount", Toast.LENGTH_SHORT).show();
                    }
                } else if (checkedId == R.id.radioButtonReset) {
                    enterNumberA.setText("");
                    enterNumberB.setText("");
                    result.setText("N/A");
                    Log.d(TAG, "Application state reset to original");
                }
            }
        });

        calculate.setOnClickListener(view -> {
           try {
               double numberA = Double.parseDouble(enterNumberA.getText().toString());
               Log.d(TAG, "numberA " + numberA);
               double numberB = Double.parseDouble(enterNumberB.getText().toString());
               Log.d(TAG, "numberB " + numberB);
               if (numberB == 0){
                   Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
               }else {
                   double discount = 1.0 * numberA / (numberB);
                   Log.d(TAG, "onCreate: " + discount);
                   result.setText(String.format("%.2f", discount));
               }

           }catch (NumberFormatException exception){
               Toast.makeText(this, "Enter both A and B to calculate discount", Toast.LENGTH_SHORT).show();
           }
        });

     reset.setOnClickListener(view -> {
            enterNumberA.setText("");
            enterNumberB.setText("");
            result.setText("N/A");
            Log.d(TAG, "Application state reset to original" );
        });
    }
}*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterNumberA = findViewById(R.id.editTextNumberA);
        enterNumberB = findViewById(R.id.editTextNumberB);
        result = findViewById(R.id.textViewResult);
        calculate = findViewById(R.id.buttonCalculate);
        reset = findViewById(R.id.buttonReset);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        textViewDivide = findViewById(R.id.textViewDivide);

        calculate.setOnClickListener(view -> {
            checkedId = radioGroup.getCheckedRadioButtonId();
            try {
                double numberA = Double.parseDouble(enterNumberA.getText().toString());
                Log.d(TAG, "numberA " + numberA);
                double numberB = Double.parseDouble(enterNumberB.getText().toString());
                Log.d(TAG, "numberB " + numberB);
                if (numberB == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkedId == R.id.radioButtonAdd){
                        textViewDivide.setText("+");
                        discount = 1.0 * numberA + (numberB);
                    } else if ((checkedId == R.id.radioButtonDiv)) {
                        discount = 1.0 * numberA / (numberB);
                        textViewDivide.setText("/");
                    } else if ((checkedId == R.id.radioButtonMul)) {
                        discount = 1.0 * numberA * (numberB);
                        textViewDivide.setText("*");
                    }else if ((checkedId == R.id.radioButtonSub)) {
                        discount = 1.0 * numberA - (numberB);
                        textViewDivide.setText("-");
                    }
                    Log.d(TAG, "onCreate: " + discount);
                    result.setText(String.format("%.2f", discount));
                }
            } catch (NumberFormatException exception) {
                Toast.makeText(this, "Enter both A and B to calculate discount", Toast.LENGTH_SHORT).show();
            }
        });

        reset.setOnClickListener(view -> {
            enterNumberA.setText("");
            enterNumberB.setText("");
            result.setText("N/A");
            Log.d(TAG, "Application state reset to original");
        });
    }
}
