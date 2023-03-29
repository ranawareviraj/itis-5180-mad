package com.example.firebaseapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firebaseapp.databinding.FragmentLoginBinding;
import com.example.firebaseapp.databinding.FragmentMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private static final String TAG = "FireAuth";
    private FirebaseAuth mAuth;
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = MainActivity.getFirebaseAuthInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login Fragment");

        binding.buttonLogin.setOnClickListener(v -> {
            String email = binding.editTextEmailAddress.getText().toString();
            String password = binding.editTextPassword.getText().toString();

            if (email.isEmpty()) {
                Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: " + " Login Successful.");
                                    Log.d(TAG, "onComplete: " + " Login Successful using Email: " + mAuth.getCurrentUser().getEmail());
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.contentView, new MainFragment())
                                            .addToBackStack(null)
                                            .commit();

                                } else {
                                    Log.d(TAG, "onFailure: " + task.getException().getMessage());
                                }
                            }
                        });
            }
        });

        binding.buttonRegister.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentView, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}