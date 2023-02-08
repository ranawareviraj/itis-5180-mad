package com.example.firstfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firstfragment.databinding.FragmentFirstBinding;


public class HomeFragment extends Fragment {

    private String education;

    public void setEducation(String education) {
        this.education = education;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("First Activity");

        if (education == null) {
            binding.textViewEducation.setText("Not Selected.!");
        } else {
            binding.textViewEducation.setText(education);
        }

       /* binding.buttonSubmit.setOnClickListener(v -> {
            String name = binding.editTextUsername.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getActivity(), "Enter Valid username", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double age = Double.parseDouble(binding.editTextAge.getText().toString());
                    Profile profile = new Profile(name, age);
                    fListener.sendProfile(profile);
                } catch (NumberFormatException exception) {
                    Toast.makeText(getActivity(), "Enter valid age", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        binding.buttonSubmit.setOnClickListener(v -> {
            String name = binding.editTextUsername.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getActivity(), "Enter Valid username", Toast.LENGTH_SHORT).show();
            } else if (education == null) {
                Toast.makeText(getActivity(), "Select Education", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double age = Double.parseDouble(binding.editTextAge.getText().toString());
                    Profile profile = new Profile(name, age, education);
                    fListener.sendProfile(profile);
                } catch (NumberFormatException exception) {
                    Toast.makeText(getActivity(), "Enter valid age", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonSelectEducation.setOnClickListener(v -> {
            fListener.goToSelectEduFragment();
        });

        binding.buttonCancel.setOnClickListener(v -> {
        });


    }

    FirstListener fListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                // context = Activity
        fListener = (FirstListener) context;    // As Activity aka context implements listener, context can be casted back to listener
    }

    public interface FirstListener {
        void sendProfile(Profile profile);

        void sendUsername(String username);

        void goToSelectEduFragment();
    }
}