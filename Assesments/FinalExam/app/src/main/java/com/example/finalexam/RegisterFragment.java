package com.example.finalexam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalexam.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    RegisterFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (RegisterFragmentListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        binding.buttonCancel.setOnClickListener(v -> {
            mListener.navigateToLogin();
        });
    }

    public interface RegisterFragmentListener {
        void navigateToLogin();
    }
}