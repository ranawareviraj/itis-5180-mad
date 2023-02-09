package com.example.assessment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment3.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {

    public void setGenderSelected(String genderSelected) {
        this.genderSelected = genderSelected;
    }

    private String genderSelected;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    RegistrationListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (RegistrationListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registration");


        if (genderSelected == null) {
            binding.textViewSelectedGender.setText("Not Selected.!");
        } else {
            binding.textViewSelectedGender.setText(genderSelected);
        }

        binding.buttonSetGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToGenderSelection();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                String gender = binding.textViewSelectedGender.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();

                } else if (gender.isEmpty() || gender.equals("Not Selected.!")) {
                    Toast.makeText(getActivity(), "Select Gender", Toast.LENGTH_SHORT).show();
                } else {
                    Profile profile = new Profile(name,gender);
                    mListener.submitRegistration(profile);
                }
            }
        });
    }

    interface RegistrationListener {
        void goToGenderSelection();

        void submitRegistration(Profile profile);
    }
}