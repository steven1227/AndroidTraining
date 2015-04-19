package com.example.steven.app1;

import android.net.Uri;

import java.net.URI;

/**
 * Created by steven on 18-4-15.
 */
public class Contact {
    private String _name;
    private String _email;
    private String _address;
    private String _phone;
    private Uri iamgeuri;
    public Contact(String name, String email, String address, String phone, Uri iamgeuri){
        this._name=name;
        this._address=address;
        this._email=email;
        this._phone=phone;
        this.iamgeuri=iamgeuri;
    }
    public String get_name(){
        return this._name;
    }
    public String get_address(){
        return this._address;
    }
    public String get_email(){
        return this._email;
    }
    public String get_phone(){
        return this._phone;
    }

    public Uri getIamgeuri() {
        return this.iamgeuri;
    }
}
