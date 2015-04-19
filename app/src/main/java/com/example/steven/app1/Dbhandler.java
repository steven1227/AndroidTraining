package com.example.steven.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steven on 19-4-15.
 */
public class Dbhandler extends SQLiteOpenHelper{

    public static final int DB_version=1;
    private static final String DB_name="contacts";
    private static final String Table_name="contacts";
    private static final String Key_ID="id";
    private static final String Key_Name="name";
    private static final String Key_Phone="phone";
    private static final String Key_Email="email";
    private static final String Key_Address="address";
    private static final String Key_uri="imageurl";


    public Dbhandler(Context context) {
        super(context, DB_name, null, DB_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table_name+"("+Key_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Key_Name+" TEXT,"+Key_Phone+" TEXT,"
        +Key_Email+" TEXT,"+Key_Address+" TEXT,"+Key_uri+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public void insert (Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Key_Name,contact.get_name());
        values.put(Key_Address,contact.get_address());
        values.put(Key_Email,contact.get_email());
        values.put(Key_Phone,contact.get_phone());
        values.put(Key_uri,contact.getIamgeuri().toString());
        db.insert(Table_name,null,values);
        db.close();
    }

    public Contact read(int id){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(Table_name,new String[]{Key_ID,Key_Name,Key_Email,Key_Address,Key_Phone,Key_uri},Key_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        Contact contact=new Contact(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Uri.parse(cursor.getString(5)),Integer.parseInt(cursor.getString(0)));
        db.close();
        return contact;
    }

    public void delete(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Table_name,Key_ID+"=?",new String[]{String.valueOf(contact.get_id())});
        db.close();
    }

    public int getcount() {
        SQLiteDatabase db = getReadableDatabase();//WritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + Table_name, null);
        int i= cursor.getCount();
        cursor.close();
        db.close();
        return i ;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Name,contact.get_name());
        values.put(Key_Address,contact.get_address());
        values.put(Key_Email,contact.get_email());
        values.put(Key_Phone,contact.get_phone());
        values.put(Key_uri,contact.getIamgeuri().toString());

        int i= db.update(Table_name,values,Key_ID+"=?",new String[]{String.valueOf(contact.get_id())});
        db.close();
        return i;
    }

    public List<Contact> getAll(){
        List<Contact> results= new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + Table_name, null);

        if(cursor.moveToFirst())
        {
           do{
                Contact contact=new Contact(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Uri.parse(cursor.getString(5)),Integer.parseInt(cursor.getString(0)));
                results.add(contact);
            }while(cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return results;
    }

}
