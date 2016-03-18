package com.scurrae.chris.feedreads;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by chris on 3/18/16.
 */
public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBAdapter adapter;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec);

        button = (Button)findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Add.class));
            }
        });

        // DBHandler instance
        DBHandler db = new DBHandler(this);

        // CRUD
        Log.d("Insert: ", "Inserting ..");

        // Read all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List <Contact> contacts = db.getAllContacts();

        for(Contact cn:contacts){
            String log = "Id: " + cn.get_id() + " , Name: " + cn.get_name() +
              " Phone: " + cn.get_phone_number();

            Log.d("Name: ", log);
        }



        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        adapter = new DBAdapter(getBaseContext(), contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        // Delete all function
        // db.deleteAllContacts();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getBaseContext(), "Create new contact", Toast.LENGTH_SHORT).show();
    }
}
