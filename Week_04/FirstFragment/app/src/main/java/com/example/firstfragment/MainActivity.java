package com.example.firstfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements HomeFragment.FirstListener, SelectEducationFragment.SelectEduListener {

    private static final String TAG = "Fragments";
    public static final String HOME_FRAGMENT = "HOME_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentView, new HomeFragment(), HOME_FRAGMENT)
                .commit();
    }

    @Override
    public void sendProfile(Profile profile) {
        Log.d(TAG, "sendProfile: " + profile);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendUsername(String username) {
        Log.d(TAG, "sendUsername: " + username);
    }

    @Override
    public void goToSelectEduFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentView, new SelectEducationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendEducation(String education) {
        //TODO
        Log.d(TAG, "sendEducation: " + education);
        // Find the fragment (Home Fragment) where data needs to be sent
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);

        if (homeFragment != null) {
            homeFragment.setEducation(education);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelEducation() {
        getSupportFragmentManager().popBackStack();
    }
}