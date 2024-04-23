package com.example.lab2_02;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvPerson;
    EditText etName;
    TextView tvSelection;
    Button btnSubmit;
    ArrayList names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPerson = findViewById(R.id.lvPerson);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);
        lvPerson.setAdapter(adapter);
        tvSelection = findViewById(R.id.tvSelection);
        btnSubmit = findViewById(R.id.btnNhap);
        etName = findViewById(R.id.etName);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String newName = etName.getText().toString().trim();
                if (!newName.isEmpty()) {
                    names.add(newName);
                    adapter.notifyDataSetChanged();
                    etName.setText("");
                }
            }
        });

        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvSelection.setText("position = " + position + " ; value = " + names.get(position));
                    }
                }
        );

        lvPerson.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        names.remove(position);
                        adapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }
}