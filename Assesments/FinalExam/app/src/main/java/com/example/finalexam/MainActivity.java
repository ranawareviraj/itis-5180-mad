package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, SearchFragment.SearchFragmentListener, RegisterFragment.RegisterFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            getSupportFragmentManager().
                    beginTransaction()
                    .add(R.id.containerView, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerView, new SearchFragment())
                    .commit();
        }
    }

    @Override
    public void navigateToSignUp() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void authSuccessful() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new SearchFragment())
                .commit();
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new LoginFragment())
                .commit();
    }

    @Override
    public void navigateToMyFavorites() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new MyFavoritesFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new LoginFragment())
                .commit();
    }
}