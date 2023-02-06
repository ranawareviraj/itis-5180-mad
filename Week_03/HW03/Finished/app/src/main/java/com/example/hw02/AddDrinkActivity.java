package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AddDrinkActivity extends AppCompatActivity {
    public static final String ADD_DRINK_ACTIVITY = "Add Drink Activity";
    RadioGroup radioGroupDrinkSize;
    TextView textViewAlcoholPercentage;
    SeekBar seekBarAlcohol;
    ArrayList<Drink> drinks =  new ArrayList<>();

    public static final String DRINKS_KEY = "DRINKS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        radioGroupDrinkSize = findViewById(R.id.radioGroupDrinkSize);
        textViewAlcoholPercentage = findViewById(R.id.textViewAlcoholPercentage);
        seekBarAlcohol = findViewById(R.id.seekBarAlcohol);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.buttonAddDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroupDrinkSize.getCheckedRadioButtonId();
                int drinkSize = 1;
                if (checkedRadioButtonId == R.id.radioButton5oz) {
                    drinkSize = 5;
                } else if (checkedRadioButtonId == R.id.radioButton12oz) {
                    drinkSize = 12;
                }
                String percentageString = textViewAlcoholPercentage.getText().toString();
                double alcoholPercentage = Double.parseDouble(percentageString.substring(0, percentageString.length()-1));
                ArrayList<Drink> drinks = (ArrayList<Drink>) getIntent().getSerializableExtra(DRINKS_KEY);
                drinks.add(new Drink(drinkSize, alcoholPercentage));
                Intent intent = new Intent();
                intent.putExtra(DRINKS_KEY, drinks);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        seekBarAlcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAlcoholPercentage.setText(progress + "");
                Log.d(ADD_DRINK_ACTIVITY, "onProgressChanged: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(ADD_DRINK_ACTIVITY, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(ADD_DRINK_ACTIVITY, "onStopTrackingTouch: ");
            }
        });
    }
}