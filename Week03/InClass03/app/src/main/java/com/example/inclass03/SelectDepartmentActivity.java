package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import javax.xml.transform.OutputKeys;

public class SelectDepartmentActivity extends AppCompatActivity {

    public static final String COMPUTER_SCIENCE = "Computer Science";
    public static final String SOFTWARE_INFO_SYSTEMS = "Software Info. Systems";
    public static final String BIO_INFORMATICS = "Bio Informatics";
    public static final String DATA_SCIENCE = "Data Science";
    public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";

    RadioGroup selectDepartment;
    Button buttonSelect, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_department);
        setTitle("Department");
        buttonSelect = findViewById(R.id.buttonSelect);
        buttonCancel = findViewById(R.id.buttonCancel);
        selectDepartment = findViewById(R.id.selectDepartment);

        buttonSelect.setOnClickListener(view -> {
            String dept = COMPUTER_SCIENCE;
            int checkedRadioButtonId = selectDepartment.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radioButtonSIS) {
                dept = SOFTWARE_INFO_SYSTEMS;
            } else if (checkedRadioButtonId == R.id.radioButtonBI) {
                dept = BIO_INFORMATICS;
            } else if (checkedRadioButtonId == R.id.radioButtonDS){
                dept = DATA_SCIENCE;
            }
            Intent intent = new Intent();
            intent.putExtra(DEPARTMENT_NAME, dept);
            setResult(RESULT_OK, intent);
            finish();
        });

        buttonCancel.setOnClickListener(view -> {
            finish();
        });

    }
}