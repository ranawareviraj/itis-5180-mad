package com.example.firstfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.firstfragment.databinding.FragmentSecondBinding;

public class SelectEducationFragment extends Fragment {
    public SelectEducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Second Activity");


        binding.buttonSelectEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = binding.radioGroup.getCheckedRadioButtonId();
                if (checkedId == -1) {
                    Toast.makeText(getActivity(), "Select Education", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedButton = binding.radioGroup.findViewById(checkedId);
                    String education = selectedButton.getText().toString();
                    mListener.sendEducation(education);
                }
            }
        });

        binding.buttonCancelEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelEducation();
            }
        });
    }

    SelectEduListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectEduListener) context;
    }

    interface SelectEduListener {
        public void sendEducation(String education);

        public void cancelEducation();
    }
}