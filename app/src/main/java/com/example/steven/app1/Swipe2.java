package com.example.steven.app1;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class Swipe2 extends FragmentActivity implements ActionBar.TabListener{
    ActionBar a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe2);
        a=getActionBar();
//        ActionBar.Tab tab1=a.newTab();
//        tab1.setText("Tab 1");
        setTitle("Rendong");
//        tab1.setTabListener(this);

//        ActionBar.Tab tab2=a.newTab();
//        tab2.setText("Tab 3");
//        tab2.setTabListener(this);
//
//        ActionBar.Tab tab3=a.newTab();
//        tab3.setText("Tab 2");
//        tab3.setTabListener(this);
//
//        a.addTab(tab1);
//        a.addTab(tab2);
//        a.addTab(tab3);
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
