package com.example.lab2_06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    CheckBox cbManager;
    Button btnAdd;
    RecyclerView rcvEmployee;
    ArrayList<Employee> employees;
    EmployeeRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        cbManager = findViewById(R.id.cbIsManager);
        btnAdd = findViewById(R.id.btnAdd);
        rcvEmployee = findViewById(R.id.rcvEmployee);
        employees = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new EmployeeRecycleAdapter(this, R.layout.item_employee,employees);
        rcvEmployee.setLayoutManager(linearLayoutManager);
        rcvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> {
            String name = etName.getText().toString();
            Employee employee = new Employee();
            if (cbManager.isChecked())
            {
                employee.setManager(true);
            }
            else
            {
                employee.setManager(false);
            }
            employee.setName(name);
            employees.add(employee);
            adapter.notifyDataSetChanged();
        });
    }
}