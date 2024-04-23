package com.example.lab3_02.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lab3_02.R;
import com.example.lab3_02.Student;
import com.example.lab3_02.adapter.StudentAdapter;
import com.example.lab3_02.database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnEditButtonClickListener, StudentAdapter.OnDeleteButtonClickListener{
    Button addStudent;
    ArrayList<Student> studentsList;
    RecyclerView rvStudent;
    StudentAdapter studentAdapter;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentsList = db.getAllStudents();
        rvStudent = findViewById(R.id.rvStudent);
        studentAdapter = new StudentAdapter(this, studentsList);
        rvStudent.setAdapter(studentAdapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(this));

        addStudent = findViewById(R.id.addStudent);
        addStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ActivityAddStudent.class);
                        startActivity(intent);
                    }
                }
        );

        studentAdapter.setOnEditButtonClickListener(this);
        studentAdapter.setOnDeleteButtonClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("NEW_STUDENT_ADDED", false)) {
            studentsList.clear();
            studentsList.addAll(db.getAllStudents());
            studentAdapter.notifyDataSetChanged();

            getIntent().removeExtra("NEW_STUDENT_ADDED");
        }
    }
    @Override
    public void onEditButtonClick(int position) {
        Student selectedStudent = studentsList.get(position);
        Intent intent = new Intent(MainActivity.this, ActivityEditStudent.class);
        intent.putExtra("SELECTED_STUDENT", selectedStudent);
        startActivity(intent);
    }

    @Override
    public void onDeleteButtonClick(int position) {
        Student selectedStudent = studentsList.get(position);
        db.deleteStudent(selectedStudent);

        studentsList.remove(position);
        studentAdapter.notifyItemRemoved(position);
    }
}