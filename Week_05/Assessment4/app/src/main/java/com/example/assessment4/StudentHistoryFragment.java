package com.example.assessment4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assessment4.Models.CourseHistory;
import com.example.assessment4.Models.DataServices;
import com.example.assessment4.Models.Student;
import com.example.assessment4.databinding.FragmentStudentHistoryBinding;

import java.util.ArrayList;


public class StudentHistoryFragment extends Fragment {

    private static final String ARG_PARAM_STUDENT = "ARG_PARAM_STUDENT";
    private Student mStudent;
    private ArrayList<CourseHistory> courseHistoryArrayList;

    public StudentHistoryFragment() {
        // Required empty public constructor
    }


    public static StudentHistoryFragment newInstance(Student student) {
        StudentHistoryFragment fragment = new StudentHistoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_STUDENT, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStudent = (Student) getArguments().getSerializable(ARG_PARAM_STUDENT);
        }
    }

    FragmentStudentHistoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    StudentsAdapter studentsAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Students");
        binding.textViewStudentName.setText(mStudent.getName());

        courseHistoryArrayList = mStudent.getCourses();
        studentsAdapter = new StudentsAdapter(getActivity(), courseHistoryArrayList);
        binding.listView.setAdapter(studentsAdapter);


    }

    class StudentsAdapter extends ArrayAdapter<CourseHistory> {
        public StudentsAdapter(@NonNull Context context, @NonNull ArrayList<CourseHistory> objects) {
            super(context, R.layout.history_row_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.history_row_item, parent, false);
            }

            TextView textViewCourseLetterGrade = convertView.findViewById(R.id.textViewCourseLetterGrade);
            TextView textViewCourseNumber = convertView.findViewById(R.id.textViewCourseNumber);
            TextView textViewCourseName = convertView.findViewById(R.id.textViewCourseName);
            TextView textViewCourseHours = convertView.findViewById(R.id.textViewCourseHours);

            CourseHistory course = getItem(position);

            textViewCourseLetterGrade.setText(course.getLetterGrade());
            textViewCourseNumber.setText(course.getNumber());
            textViewCourseName.setText(course.getName());
            textViewCourseHours.setText(course.getHours() + " Hours");

            return convertView;
        }
    }
}