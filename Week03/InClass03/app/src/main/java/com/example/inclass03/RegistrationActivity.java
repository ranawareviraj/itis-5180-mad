package com.example.inclass03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Profile profile;
    TextView editTextName, editTextEmail, editTextId, textViewDepartment;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result != null && result.getResultCode() == RESULT_OK) {
                                if (result.getData() != null &&
                                        result.getData().getStringExtra(SelectDepartmentActivity.DEPARTMENT_NAME) != null) {
                                    textViewDepartment.setText("" + result.getData().getStringExtra(SelectDepartmentActivity.DEPARTMENT_NAME));
                                }
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextId = findViewById(R.id.editTextId);
        textViewDepartment = findViewById(R.id.textViewDepartment);

        findViewById(R.id.buttonSelectDept).setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, SelectDepartmentActivity.class);
            startForResult.launch(intent);
        });

        findViewById(R.id.buttonSubmit).setOnClickListener(view -> {
            profile = new Profile();
            try {
                profile.setName(editTextName.getText().toString());
                profile.setEmail(editTextEmail.getText().toString());
                profile.setId(Integer.parseInt(editTextId.getText().toString()));
                profile.setDepartment(textViewDepartment.getText().toString());
                Intent intent = new Intent(RegistrationActivity.this, ProfileActivity.class);
                intent.putExtra("PROFILE", profile);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            } catch (NumberFormatException exception) {
                Toast.makeText(this, "Enter valid numerical ID", Toast.LENGTH_SHORT).show();
            }
        });
    }
}