package com.example.lab2_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etID, etName;
    CheckBox cbManager;
    Button btnAdd;
    ListView lvEmployee;
    ArrayList<Employee> employees;
    EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etFullname);
        cbManager = findViewById(R.id.rbIsManager);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployee = findViewById(R.id.lvEmployee);
        employees = new ArrayList<>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployee.setAdapter(adapter);


        btnAdd.setOnClickListener(view -> {
            String id = etID.getText().toString();
            String name = etName.getText().toString();
            Employee employee = new Employee(id, name);
            if (cbManager.isChecked()) {
                employee.setManager(true);
            } else {
                employee.setManager(false);
            }
            employees.add(employee);

            adapter.notifyDataSetChanged();
        });

    }
}