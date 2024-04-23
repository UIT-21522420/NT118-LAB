package com.example.lab2_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerDish;
    SpinnerAdapter spinnerAdapter;
    Dish resDish;
    Button btnAdd;
    GridView gvDish;
    EditText etName;
    ArrayList<Dish> arrayDish;
    ArrayList<Dish> DishList;
    DishAdapter adapter;
        CheckBox cbPromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayDish = new ArrayList<>();
        arrayDish.add(new Dish("Phở", R.drawable.first_thumbnail));
        arrayDish.add(new Dish("Bún bò huế", R.drawable.second_thumbnail));
        arrayDish.add(new Dish("Bánh mì", R.drawable.third_thumbnail));
        arrayDish.add(new Dish("Bún đậu mắm tôm", R.drawable.fourth_thumbnail));
        arrayDish.add(new Dish("Bánh canh cá", R.drawable.fifth_thumbnail));

        spinnerDish = findViewById(R.id.spinner);

        spinnerAdapter = new com.example.lab2_05.SpinnerAdapter(getApplicationContext(), R.layout.dropdown_list, arrayDish);
        spinnerDish.setAdapter(spinnerAdapter);


        spinnerDish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                resDish = arrayDish.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        cbPromotion = findViewById(R.id.cbPromotion) ;
        btnAdd  = findViewById(R.id.btnAdd) ;
        gvDish = findViewById(R.id.gvDish);

        etName = findViewById(R.id.etName);
        DishList = new ArrayList<>();
        adapter = new DishAdapter(this, R.layout.dish_item,DishList);
        gvDish.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> {
            String name = etName.getText().toString();
            Dish Dish = new Dish();
            Dish.setName(name);

            Dish.setThumbnail(resDish.getThumbnail());

            if (cbPromotion.isChecked())
            {
                Dish.setPromotion(true);
            }
            else
            {
                Dish.setPromotion(false);
            }

            DishList.add(Dish);
            etName.setText("");
            spinnerDish.setSelection(0);
            cbPromotion.setChecked(false);
            adapter.notifyDataSetChanged();
        });
    }
}