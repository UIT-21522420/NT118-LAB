package com.example.lab3_02.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab3_02.R;
import com.example.lab3_02.Student;
import com.example.lab3_02.database.DatabaseHelper;

public class ActivityAddStudent extends AppCompatActivity {
    Button btnAddStudent;
    EditText etName, etMssv, etEmail;
    Spinner spinnerKhoa;
    String selectedKhoa;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        etName = findViewById(R.id.etName);
        etMssv = findViewById(R.id.etMssv);
        etEmail = findViewById(R.id.etEmail);
        spinnerKhoa = findViewById(R.id.spinnerKhoa);

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

        btnAddStudent.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String mssv = etMssv.getText().toString();
            db.addStudent(new Student(name, email, mssv, selectedKhoa));

            Intent intent = new Intent(ActivityAddStudent.this, MainActivity.class);
            intent.putExtra("NEW_STUDENT_ADDED", true);
            startActivity(intent);
        });
    }
}
