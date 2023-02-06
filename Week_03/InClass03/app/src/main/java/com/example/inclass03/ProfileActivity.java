package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView profileName, profileEmail, profileId, profileDept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileId = findViewById(R.id.profileId);
        profileDept = findViewById(R.id.profileDept);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("PROFILE")) {
            Profile profile = (Profile) getIntent().getSerializableExtra("PROFILE");
            profileName.setText(profile.getName());
            profileEmail.setText(profile.getEmail());
            profileId.setText(profile.getId() + "");
            profileDept.setText(profile.getDepartment());
        }
    }
}

/*
            if (getIntent().hasExtra("NAME")) {
                profileName.setText(getIntent().getStringExtra("NAME"));
            } else if (getIntent().hasExtra("EMAIL")) {
                profileEmail.setText(getIntent().getStringExtra("EMAIL"));
            }else if (getIntent().hasExtra("ID")) {
                profileId.setText(getIntent().getStringExtra("ID"));
            }else if (getIntent().hasExtra("DEPARTMENT")) {
                profileDept.setText(getIntent().getStringExtra("DEPARTMENT"));
            }*/

  /*  ActivityResultLauncher<Intent> startForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result != null && result.getResultCode() == RESULT_OK) {
                                if (result.getData() != null && result.getData().getStringExtra("NAME") != null) {
                                    profileName.setText(result.getData().getStringExtra("NAME"));
                                }
                            }
                        }
                    });*/
