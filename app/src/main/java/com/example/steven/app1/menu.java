package com.example.steven.app1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by steven on 2-3-15.
 */
public class menu extends ListActivity{
   String [] temp ={"MainActivity","Testplay","Email","Camera","Data","Openclass","Map","GFX","TabActivity","Surface","Blank"};
    private ArrayList<String> classes=new ArrayList<>(Arrays.asList(temp));

    ArrayAdapter<String> menulist;
    @Override
    protected void onCreate(Bundle savedstate)
    {
        super.onCreate(savedstate);

        //set fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        menulist=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1
                ,classes);

        super.setListAdapter(menulist);

        classes.add("test1");
        classes.add("test2");
        classes.add("test3");
        classes.add("test4");
//        menulist.add("t5");
        //classes.clear();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (true) {
            String ourclass = null;
                classes.add("test5");
                menulist.notifyDataSetChanged();
                //super.setListAdapter(menulist);

                ourclass = "com.example.steven.app1." + classes.get(position);
                Intent ourIntent = new Intent(ourclass);
                startActivity(ourIntent);


        }
    }


}
