package com.scurrae.chris.feedreads;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteException;
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

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chris on 3/18/16.
 */
public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBAdapter adapter;
    private Button button;
    private Button button2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        button = (Button)findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Add.class));
            }
        });

        // DBHandler instance
        final DBHandler db = new DBHandler(this);

        button2 = (Button)findViewById(R.id.del);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteAllContacts();
                } catch (SQLiteException e){
                    e.printStackTrace();
                } finally {
                    setContentView(R.layout.rec);
                }

            }
        });

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



    }

}
