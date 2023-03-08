package com.example.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.AppCategoriesListener, AppsListFragment.AppsListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, new AppCategoriesFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendAppCategory(String category) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, AppsListFragment.newInstance(category))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSelectedApp(DataServices.App app) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootView, AppDetailsFragment.newInstance(app))
                .addToBackStack(null)
                .commit();
    }
}