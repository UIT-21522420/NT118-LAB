package com.example.lab3_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Lab03_01 extends AppCompatActivity {
    ArrayList<String> contactsList;
    ListView lvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact(1,"Ravi", "9100000000"));
        db.addContact(new Contact(2,"Srinivas", "9199999999"));
        db.addContact(new Contact(3,"Tommy", "9522222222"));
        db.addContact(new Contact(4,"Karthik", "9533333333"));

        lvUser = findViewById(R.id.lvUser);
        contactsList = new ArrayList<>();

        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " - Name: " + cn.getName() + " - Phone: " + cn.getPhoneNumber();
            contactsList.add(log);
            Log.e("Name: ", log);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        lvUser.setAdapter(adapter);

        lvUser.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    contactsList.remove(position);
                    db.deleteContact(db.getContact(position));
                    adapter.notifyDataSetChanged();
                    for (Contact cn : contacts) {
                        String log = "Id: " + cn.getID() + " - Name: " + cn.getName() + " - Phone: " + cn.getPhoneNumber();
                        Log.e("Name: ", log);
                    }
                    return true;
                }
        );
    }
}