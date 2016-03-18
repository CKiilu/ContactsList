package com.scurrae.chris.feedreads;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
                " TEXT," + KEY_PH_NO + " TEXT" + ")";
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
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows to add to list
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));
                // Adding single contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());

        }
        // Return contact list
        return contactList;
    }
    // To get total contacts
    public int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,  null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    public int updateContact(Contact contact){
        // DB instance
        SQLiteDatabase db = this.getWritableDatabase();

        // Adding values to db
        ContentValues values  = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_PH_NO, contact.get_phone_number());

        // Adding rows
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(contact.get_id())});
    }
    // Deleting a contact
    public void deleteContact(Contact contact){
        // DB instance
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[]{String.valueOf(contact.get_id())});
        db.close();
    }
    // Delete all contacts
    public void deleteAllContacts(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,null,null);
        db.execSQL("delete from "+ TABLE_CONTACTS);
        db.execSQL("TRUNCATE table " + TABLE_CONTACTS);
        db.close();

    }
}
