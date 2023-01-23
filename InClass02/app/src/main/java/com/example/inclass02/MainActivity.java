package com.example.inclass02;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText ticketPrice;
    TextView discountPercentage;
    TextView discountedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ticketPrice = findViewById(R.id.ticket_price_value);
        discountPercentage = findViewById(R.id.discounted_percent_value);
        discountedPrice = findViewById(R.id.discounted_price_value);

        findViewById(R.id.discount_percent_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay(5.00);
            }
        });

        findViewById(R.id.discount_percent_10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay(10.00);
            }
        });

        findViewById(R.id.discount_percent_15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay(15.00);
            }
        });

        findViewById(R.id.discount_percent_20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay(20.00);
            }
        });

        findViewById(R.id.discount_percent_50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay(50.00);
            }
        });

        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketPrice.setText("");
                discountPercentage.setText("");
                discountedPrice.setText("");
            }
        });
    }

    public void calculateAndDisplay(double discountSelected) {
        String ticketPriceEntered = ticketPrice.getText().toString();
        try {
            double amount = Double.parseDouble(ticketPriceEntered);
            double discountedAmount = (100 - discountSelected) * amount / 100.0;
            discountedPrice.setText(new DecimalFormat("0.00").format(discountedAmount));
            discountPercentage.setText(String.valueOf(discountSelected));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter valid numerical price..!", Toast.LENGTH_SHORT).show();
        }
    }
}