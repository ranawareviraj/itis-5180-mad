package com.example.assessment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.WelcomeListener,
        RegistrationFragment.RegistrationListener, SetGenderFragment.SetGenderListener,
        ProfileFragment.ProfileListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootView, new WelcomeFragment())
                .commit();

    }

    @Override
    public void goToRegistration() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, new RegistrationFragment(), "RegistrationFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToGenderSelection() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, new SetGenderFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void submitRegistration(Profile profile) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendGender(String gender) {
        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("RegistrationFragment");
        if (registrationFragment != null) {
            registrationFragment.setGenderSelected(gender);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelGender() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeProfile() {
        getSupportFragmentManager().popBackStack();
    }
}