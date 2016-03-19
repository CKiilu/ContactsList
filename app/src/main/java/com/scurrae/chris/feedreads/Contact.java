package com.scurrae.chris.feedreads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class Contact {
    // private vars
    int _id;
    String _name;
    String _phone_number;

    // Constructor
    public Contact(){

    }
    // Constructor with id
    public Contact(int id, String name, String phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = phone_number;
    }
    // Constructor without id
    public Contact(String name, String phone_number){
        this._name = name;
        this._phone_number = phone_number;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}


