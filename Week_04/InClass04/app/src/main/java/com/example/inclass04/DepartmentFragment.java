package com.example.inclass04;

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

import com.example.inclass04.databinding.FragmentDepartmentBinding;


public class DepartmentFragment extends Fragment {

//    private String mDepartment;

    public DepartmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentDepartmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDepartmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Department Fragment");

        binding.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = binding.radioGroupDept.getCheckedRadioButtonId();
                if (checkedId == -1) {
                    Toast.makeText(getActivity(), "Select Department", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedButton = binding.radioGroupDept.findViewById(checkedId);
                    String department = selectedButton.getText().toString();
                    mListener.sendDepartment(department);
                }
            }
        });

        binding.buttonCancelDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelDepartment();
            }
        });
    }

    SelectDeptListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectDeptListener) context;
    }

    interface SelectDeptListener {
        public void sendDepartment(String education);

        public void cancelDepartment();
    }
}