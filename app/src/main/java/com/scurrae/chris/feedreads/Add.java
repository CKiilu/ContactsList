package com.scurrae.chris.feedreads;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 3/18/16.
 */
public class Add extends AppCompatActivity {

    private Map<String, String> contact = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Firebase.setAndroidContext(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Call views, db and buttons
        final EditText name = (EditText)findViewById(R.id.editname);
        final EditText num = (EditText)findViewById(R.id.editnum);
        Button c = (Button)findViewById(R.id.cancelbut);
        Button d = (Button)findViewById(R.id.donebut);
        Firebase myFirebaseRef = new Firebase("https://shortshotie.firebaseio.com/");
        final Firebase contactRef = myFirebaseRef.child("contacts");
        final Firebase contactId = contactRef.push();

        // Create button array
        final Button[] bArray = {c, d};

        // On click functions
        for(final Button b:bArray){
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            // Cancel button
                            case R.id.cancelbut:
                                try {
                                    finish();
                                } catch (NullPointerException e){
                                    e.printStackTrace();
                                } finally {
                                    finish();
                                }
                                break;
                            // done button
                            case R.id.donebut:
                                String person = String.valueOf(name.getText());
                                String phone = String.valueOf(num.getText());
//                                if(person != null && phone != null) {
//                                    contact.put(person, phone);
//                                    contactId.setValue(contact);
//                                }
                                if(person != null && phone != null) {
                                    contactRef.child(person).setValue(phone);
                                }
                                    finish();
                                break;
                            default:
                                break;
                        }

                    }
                });
        }
    }


}
