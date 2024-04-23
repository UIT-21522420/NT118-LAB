package com.example.lab2_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etManv, etTennv;
    RadioGroup rgLoainv;
    RadioButton rbFulltime, rbParttime;
    Button btnSubmit;
    TextView tvSelection;
    ListView lvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etManv = findViewById(R.id.etManv);
        etTennv = findViewById(R.id.etTennv);
        rgLoainv = findViewById(R.id.rgLoainv);
        rbFulltime = findViewById(R.id.rbFulltime);
        rbParttime = findViewById(R.id.rbParttime);
        tvSelection = findViewById(R.id.tvSelection);
        btnSubmit = findViewById(R.id.btnNhapnv);
        lvPerson = findViewById(R.id.lvPerson);
        ArrayList<String> personList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, personList);
        lvPerson.setAdapter(adapter);

        btnSubmit.setOnClickListener(arg0 -> {
            String newManv = etManv.getText().toString().trim();
            String newTennv = etTennv.getText().toString().trim();
            int selectedLoainv = rgLoainv.getCheckedRadioButtonId();
            if (newManv.isEmpty() || newTennv.isEmpty() || selectedLoainv == -1) {
                tvSelection.setText("Thiếu thông tin!");
            } else {
                Employee newE;
                RadioButton rbSelectedLoainv = findViewById(selectedLoainv);
                if(rbSelectedLoainv.getId() == R.id.rbFulltime) {
                    newE = new EmployeeFullTime(newManv, newTennv);
                } else {
                    newE = new EmployeePartTime(newManv, newTennv);
                }
                personList.add(newE.toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}