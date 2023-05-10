package com.example.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalexam.databinding.ActivityMainBinding;
import com.example.finalexam.models.CoverPhoto;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginInterface, RegisterFragment.RegisterInterface, SearchFragment.SearchInterface, MyFavoritesFragment.FavInterface {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       if(FirebaseAuth.getInstance().getCurrentUser() == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootView, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootView, new SearchFragment())
                    .commit();
        }
    }


    @Override
    public void login() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void authSuccessful() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SearchFragment())
                .commit();
    }

    @Override
    public void createNewAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegisterFragment())
                .commit();
    }

    @Override
    public void logout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment())
                .commit();
    }

    @Override
    public void gotoFav() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new MyFavoritesFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDetails(CoverPhoto coverPhoto) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,  PhotoDetailsFragment.newInstance(coverPhoto))
                .addToBackStack(null)
                .commit();
    }
}