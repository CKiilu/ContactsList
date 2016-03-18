package com.scurrae.chris.feedreads;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

/**
 * Created by chris on 3/18/16.
 */
public class Main extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // DBHandler instance
        DBHandler db = new DBHandler(this);

        // CRUD
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Read all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List <Contact> contacts = db.getAllContacts();

        for(Contact cn:contacts){
            String log = "Id: " + cn.get_id() + " , Name: " + cn.get_name() +
              " Phone: " + cn.get_phone_number();

            Log.d("Name: ", log);
        }
    }
}
