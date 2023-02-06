package com.example.hw02;

import static com.example.hw02.AddDrinkActivity.DRINKS_KEY;
import static com.example.hw02.SetProfileActivity.PROFILE_KEY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "BAC Calculator App : MainActivity";
    public static final String STATUS_SAFE = "You're Safe";
    public static final String STATUS_CAREFUL = "Be Careful";
    public static final String STATUS_OVERLIMIT = "Over the Limit";

    TextView textViewNoDrinks, textViewBACLevel, textViewStatus, textViewWeightGender;
    View viewStatus;
    Button buttonSetProfile, buttonViewDrinks, buttonAddDrink, buttonReset;

    ArrayList<Drink> drinks = new ArrayList<>();
    double BAC = 0.000;
    Profile profile;

    private final ActivityResultLauncher<Intent> startForProfileResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData().hasExtra(PROFILE_KEY)) {
                        Intent data = result.getData();
                        //use the returned data

                        //i. The received weight and gender should be stored in the Main Activity, and should be displayed as shown in Fig 1(d).
                        profile = (Profile) data.getSerializableExtra(PROFILE_KEY);
                        textViewWeightGender.setText(new StringBuilder().append(profile.getWeight()).append(" lbs (").append(profile.getGender()).append(")").toString());

                        // ii. Clear the drinks list, clear the BAC and UI as shown in Fig 1(d).
                        // TODO

                        // iii. Enable the View Drinks and Add Drink buttons.
                        buttonViewDrinks.setEnabled(true);
                        buttonAddDrink.setEnabled(true);
                    } else {
                        textViewWeightGender.setText("N/A");
                        buttonViewDrinks.setEnabled(false);
                        buttonAddDrink.setEnabled(false);
                    }
                }
            });

    private final ActivityResultLauncher<Intent> startForDrinksResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        if (result.getData().hasExtra(DRINKS_KEY)) {
                            Intent data = result.getData();
                            //use the returned data
                            drinks = (ArrayList<Drink>) data.getSerializableExtra(DRINKS_KEY);
                            textViewNoDrinks.setText("# Drinks: " + drinks.size() + "");
                            calculateBAC();
                            updateBACValueAndStatus();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Add drinks first!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize textViews
        textViewNoDrinks = findViewById(R.id.textViewNoDrinks);
        textViewBACLevel = findViewById(R.id.textViewBACLevel);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewWeightGender = findViewById(R.id.textViewWeightGender);
        viewStatus = findViewById(R.id.viewStatus);

        // Initialize buttons
        buttonSetProfile = findViewById(R.id.buttonSetProfile);
        buttonViewDrinks = findViewById(R.id.buttonViewDrinks);
        buttonAddDrink = findViewById(R.id.buttonAddDrink);
        buttonReset = findViewById(R.id.buttonReset);

        // Initial state of an app
        textViewWeightGender.setText("N/A");
        buttonViewDrinks.setEnabled(false);
        buttonAddDrink.setEnabled(false);

        buttonSetProfile.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SetProfileActivity.class);
            startForProfileResult.launch(intent);
        });

        buttonViewDrinks.setOnClickListener(view -> {
            if (drinks.size() == 0) {
                Toast.makeText(this, "User has no Drinks added..!, Add Drinks first.", Toast.LENGTH_SHORT).show();
            } else {

            }
        });

        buttonAddDrink.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddDrinkActivity.class);
            intent.putExtra(DRINKS_KEY, drinks);
            startForDrinksResult.launch(intent);
        });

        buttonReset.setOnClickListener(view -> {
            drinks.clear();
            textViewWeightGender.setText("N/A");
            buttonViewDrinks.setEnabled(false);
            buttonAddDrink.setEnabled(false);
            textViewNoDrinks.setText("# Drinks: 0");
            textViewBACLevel.setText("BAC Level: 0.000");
            textViewStatus.setText(STATUS_SAFE);
            viewStatus.setBackgroundColor(getColor(R.color.safe_color));
        });
    }

    private void calculateBAC() {
        double A = 0.0;
        double r = profile.getGender().equals("Female") ? 0.66 : 0.73;
        double W = profile.getWeight();

        for (Drink drink : drinks) {
            A += drink.getDrinkSize() * drink.getAlcoholPercentage() / 100.0;
            Log.d(TAG, "UpdateStatus A: " + A);
        }
        BAC = (A * 5.14 / (W * r));
        Log.d(TAG, "UpdateStatus: BAC" + BAC);

    }

    private void updateBACValueAndStatus() {
        textViewBACLevel.setText("BAC Level: " + String.format("%.3f", BAC));
        if (BAC < 0.08) {
            textViewStatus.setText(STATUS_SAFE);
            viewStatus.setBackgroundColor(getColor(R.color.safe_color));
        } else if (BAC < 0.2) {
            textViewStatus.setText(STATUS_CAREFUL);
            viewStatus.setBackgroundColor(getColor(R.color.becareful_color));
        } else {
            textViewStatus.setText(STATUS_OVERLIMIT);
            viewStatus.setBackgroundColor(getColor(R.color.overlimit_color));
            buttonAddDrink.setEnabled(false);
        }
    }
}
