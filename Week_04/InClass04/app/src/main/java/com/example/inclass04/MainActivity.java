package com.example.inclass04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.inclass04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.WelcomeListener, RegistrationFragment.RegistrationListener, DepartmentFragment.SelectDeptListener {

    public static final String REGISTRATION_FRAGMENT = "REGISTRATION_FRAGMENT";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.welcome_container, new WelcomeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.welcome_container, new RegistrationFragment(), REGISTRATION_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDepartment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.welcome_container, new DepartmentFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendProfile(Profile profile) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.welcome_container, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendDepartment(String department) {
        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag(REGISTRATION_FRAGMENT);

        if (registrationFragment != null) {
            registrationFragment.setDepartment(department);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelDepartment() {
        getSupportFragmentManager().popBackStack();
    }
}