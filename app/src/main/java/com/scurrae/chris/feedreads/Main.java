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

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by chris on 3/18/16.
 */
public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBAdapter adapter;
    private Button button;
    private Button button2;
    List<Contact> contacts = new ArrayList<>();
    private Firebase myFirebaseRef = new Firebase("https://shortshotie.firebaseio.com/");

    private Firebase contactRef = myFirebaseRef.child("contacts");

    private List<Contact> getData(){
        // Firebase get call
        contactRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    contacts.add(new Contact(d.getKey(), (String)d.getValue()));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return contacts;
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
                startActivity(new Intent(getBaseContext(), Add.class));
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
        adapter = new DBAdapter(getBaseContext(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));



    }

}
