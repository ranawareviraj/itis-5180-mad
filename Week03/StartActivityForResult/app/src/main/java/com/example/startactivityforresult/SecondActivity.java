package com.example.startactivityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    public static final String NAME = "NAME";
    Button buttonGoBack, buttonCancel;
    EditText editTextPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonGoBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            String name = editTextPersonName.getText().toString();
            intent.putExtra(NAME, name);
            setResult(RESULT_OK, intent);
            finish();
        });

        buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}