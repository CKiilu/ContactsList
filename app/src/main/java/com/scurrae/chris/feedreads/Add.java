package com.scurrae.chris.feedreads;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chris on 3/18/16.
 */
public class Add extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Call views, db and buttons
        final EditText name = (EditText)findViewById(R.id.editname);
        final EditText num = (EditText)findViewById(R.id.editnum);
        Button c = (Button)findViewById(R.id.cancelbut);
        Button d = (Button)findViewById(R.id.donebut);
        final DBHandler db = new DBHandler(getBaseContext());

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
                                startActivity(new Intent(getBaseContext(), Main.class));
                                break;
                            // done button
                            case R.id.donebut:
                                String person = String.valueOf(name.getText());
                                String phone = String.valueOf(num.getText());
                                if(person != null && phone != null) {
                                    db.addContact(new Contact(person, phone));
                                }
                                startActivity(new Intent(getBaseContext(), Main.class));
                                break;
                            default:
                                break;
                        }

                    }
                });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
