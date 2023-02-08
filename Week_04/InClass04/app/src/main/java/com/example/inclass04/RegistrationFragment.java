package com.example.inclass04;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inclass04.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {
    String department;

    public void setDepartment(String department) {
        this.department = department;
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString("ARG_PARAM1");
        }
    }

    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registration Fragment");

        if (department == null) {
            binding.textViewDepartment.setText("Not Selected.!");
        } else {
            binding.textViewDepartment.setText(department);
        }

        // Selecting the department
        binding.buttonSelectDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationListener.goToDepartment();
            }
        });

        // Submit form
        binding.buttonSubmitRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextPersonName.getText().toString();
                String email = binding.editTextEmailAddress.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (department == null){
                    Toast.makeText(getActivity(), "Select the department", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        int id = Integer.parseInt(binding.editTextID.getText().toString());
                        Profile profile = new Profile(name, email, id, department);
                        registrationListener.sendProfile(profile);
                    }catch (NumberFormatException exception){
                        Toast.makeText(getActivity(), "Enter valid ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    RegistrationListener registrationListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        registrationListener = (RegistrationListener) context;
    }

    interface RegistrationListener {
        public void goToDepartment();
        public void sendProfile(Profile profile);
    }
}