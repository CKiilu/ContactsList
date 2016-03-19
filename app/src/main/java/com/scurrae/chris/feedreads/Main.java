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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 3/18/16.
 */
public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBAdapter adapter;
    private Button button;
    private Button button2;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String nameSentBack = data.getStringExtra("Name");
        String numSentBack = data.getStringExtra("Phone_Number");
        contacts.add(new Contact(nameSentBack, numSentBack));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Firebase.setAndroidContext(this);

        button = (Button)findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),
                        Add.class);
                final int result = 1;
                i.putExtra("addNew", "Main");
                startActivityForResult(i, result);
            }
        });

        button2 = (Button)findViewById(R.id.del);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (Contact a : contacts) {
                        contacts.remove(a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    setContentView(R.layout.rec);
                }

            }
        });

        // CRUD
        Log.d("Insert: ", "Inserting ..");
        contacts.add(new Contact("Job", "09876532"));


        // Read all contacts
        Log.d("Reading: ", "Reading all contacts..");



        for(Contact cn:contacts){
            String log = "Name: " + cn.get_name() +
              " Phone: " + cn.get_phone_number();

            Log.d("Name: ", log);
        }



        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        adapter = new DBAdapter(getBaseContext(), contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));



    }

}
