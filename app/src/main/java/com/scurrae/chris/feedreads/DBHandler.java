package com.scurrae.chris.feedreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chris on 3/18/16.
 */
public class DBHandler extends SQLiteOpenHelper {
    // Static vars
    // DB version
    private  static final int DATABASE_VERSION = 1;
    // DB name
    private  static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private  static final String TABLE_CONTACTS = "contacts";
    // Tables column names
    private  static final String KEY_ID = "id";
    private  static final String KEY_NAME = "name";
    private  static final String KEY_PH_NO = "phone_number";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Table creation


    @Override
    public void onCreate(SQLiteDatabase db) {
        // String containing all vars needed
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                " TEXT," + KEY_PH_NO + " TEXT," + ")";
        // Execute the string
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create new table
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact){
        // Instanciate new DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Values being added to db
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        // Adding new row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();

    }
    public Contact getContact(int id){
        // Instanciate DB
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor selector

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{
        KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        // Cursor != null ?
        if (cursor != null){
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return contact;

    }
}
