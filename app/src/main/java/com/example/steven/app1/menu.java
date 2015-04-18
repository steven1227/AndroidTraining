package com.example.steven.app1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by steven on 2-3-15.
 */
public class menu extends ListActivity{
    private String classes[]={"MainActivity","Testplay","Email","Camera","Data","Openclass","Map","example7","example8","example9"};



    @Override
    protected void onCreate(Bundle savedstate)
    {
        super.onCreate(savedstate);

        super.setListAdapter(new ArrayAdapter<String>(this,
                         android.R.layout.simple_list_item_1
                         ,classes)

        );
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.v("ac1","ac2");
        if(true) {
            Class ourclass = null;
            try {
                ourclass = Class.forName("com.example.steven.app1."+classes[position]);
                Intent ourIntent = new Intent(this, ourclass);
                startActivity(ourIntent);
            } catch (ClassNotFoundException e) {
                Log.v("ac1","ac2");
                e.printStackTrace();
            }


        }

    }



}
