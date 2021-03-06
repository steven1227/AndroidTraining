package com.example.steven.app1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class Blank extends ActionBarActivity implements communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(getClass().getSimpleName(), "OnCreate");
        setContentView(R.layout.activity_blank);
//
//        MyFragment2 frag=new MyFragment2();
//        MyFragment frag1=new MyFragment();
//        FragmentManager manager=getFragmentManager();
//        FragmentTransaction trans=manager.beginTransaction();
//
//        trans.add(R.id.blank,frag1,"myfragment1");
//        trans.add(R.id.blank,frag,"myfragment2");
//        trans.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blank, menu);
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
    public void respond(String data) {
        FragmentManager manager=getFragmentManager();
        MyFragment2 target= (MyFragment2)manager.findFragmentById(R.id.fragment2);
//        MyFragment target1= (MyFragment)manager.findFragmentById(R.id.fragment1);
//        Log.d(getClass().getSimpleName(),"~~~~"+target1.getString(R.string.hello_blank_fragment));
//        Log.d(getClass().getSimpleName(),"~~~~"+target.getTag());
        target.changedData(data);

    }
}
