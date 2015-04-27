package com.example.steven.app1;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Blank3 extends ActionBarActivity implements MyListfragment.comm{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank3);
    }

    public void showdialog(View e){
        FragmentManager manager=getFragmentManager();
        MyListfragment mydialog=new MyListfragment();
        mydialog.show(manager,"mydialog");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blank3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Onshow(String messege) {
        Toast.makeText(this,messege,Toast.LENGTH_SHORT).show();

    }

    public void showAlert( View v){
        Myalert mydialog=new Myalert();
        mydialog.show(getFragmentManager(),"myalert");

    }
}
