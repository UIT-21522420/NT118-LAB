package com.example.lab2_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView lvPerson;
    TextView tvSelection;
    final String arr[] = {"Tèo","Tý","Bin","Bo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPerson = findViewById(R.id.lvPerson);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr);
        lvPerson.setAdapter(adapter);
        tvSelection = findViewById(R.id.tvSelection);
        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tvSelection.setText("position: " + position + " ; value: " + arr[position]);
                    }
                }
        );
    }
}