package com.example.inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.inclass05.databinding.FragmentAppCategoriesBinding;

import java.util.ArrayList;


public class AppCategoriesFragment extends Fragment {
    public AppCategoriesFragment() {
        // Required empty public constructor
    }

    FragmentAppCategoriesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAppCategoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayAdapter<String> adapter;
    ArrayList<String> categories;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    AppCategoriesListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppCategoriesListener) context;
    }

    interface AppCategoriesListener{
        void sendSelectedCategory(String category);
    }


}