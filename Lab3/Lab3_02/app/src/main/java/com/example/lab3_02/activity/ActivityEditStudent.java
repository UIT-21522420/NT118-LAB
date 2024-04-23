package com.example.lab3_02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab3_02.R;
import com.example.lab3_02.Student;
import com.example.lab3_02.adapter.StudentAdapter;
import com.example.lab3_02.database.DatabaseHelper;

import java.util.Arrays;

public class ActivityEditStudent extends AppCompatActivity {
    EditText etName, etEmail;
    TextView tvMssv2;
    Spinner spinnerKhoa;
    Button btnEditStudent;
    String selectedKhoa;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etName = findViewById(R.id.etName);
        tvMssv2 = findViewById(R.id.tvMssv2);
        etEmail = findViewById(R.id.etEmail);
        spinnerKhoa = findViewById(R.id.spinnerKhoa);
        btnEditStudent = findViewById(R.id.btnEditStudent);
        db = new DatabaseHelper(this);

        Student selectedStudent = (Student) getIntent().getSerializableExtra("SELECTED_STUDENT");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKhoa.setAdapter(adapter);

        spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKhoa = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etName.setText(selectedStudent.getName());
        tvMssv2.setText(selectedStudent.getMssv());
        etEmail.setText(selectedStudent.getEmail());

        String[] khoaOptions = getResources().getStringArray(R.array.dropdown_options_array);
        int selectedKhoaIndex = Arrays.asList(khoaOptions).indexOf(selectedStudent.getKhoa());
        spinnerKhoa.setSelection(selectedKhoaIndex);

        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedName = etName.getText().toString();
                String updatedEmail = etEmail.getText().toString();

                selectedStudent.setName(updatedName);
                selectedStudent.setEmail(updatedEmail);
                selectedStudent.setKhoa(selectedKhoa);

                db.updateStudent(selectedStudent);

                Intent intent = new Intent(ActivityEditStudent.this, MainActivity.class);
                intent.putExtra("NEW_STUDENT_ADDED", true);
                startActivity(intent);
            }
        });
    }
}
