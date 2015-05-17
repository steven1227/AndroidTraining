package com.example.steven.app1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Draw2 extends Activity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayList<String> planets;
    private ActionBarDrawerToggle drawlisten;

    private static int progress;
    private ProgressBar bar2;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw2);
        listView=(ListView)findViewById(R.id.drawerlist);
        Drawable background = listView.getBackground();
        background.setAlpha(100);

        planets=new ArrayList<>();

//        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, planets));
        /***
         * we can use the easy list Adapter
         * but if you want a custom one, create a custom one adapter class for your self
         */
        listView.setAdapter(new Mydapter());
        listView.setOnItemClickListener(this);
        planets.add("Facebook");
        planets.add("Youtube");
        planets.add("Twitter");
        planets.add("Linkedin");


        this.bar2=(ProgressBar)findViewById(R.id.progrss);
        this.bar2.setMax(200);
//        bar2.setProgress(100);



        this.drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        drawlisten=new ActionBarDrawerToggle(this,this.drawerLayout,R.drawable.ic_launcher,R.drawable.ic_launcher_web){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(),"open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(Draw2.this,"close",Toast.LENGTH_SHORT).show();
            }


        };
        drawerLayout.setDrawerListener(this.drawlisten);
        ActionBar bar=getActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);

        Button start=(Button)findViewById(R.id.button6);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosomework1();
            }


        });


    }

    private void dosomework1() {
        progress=0;

        Thread thread=new Thread(){
          public void run()  {
              while(progress<200){
                  try {
                      sleep(20);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                      bar2.setProgress(progress);
                      progress++;
//                    handler.post(this);
              }
            if(progress==200)
            {
                Draw2.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Draw2.this, "Finish", Toast.LENGTH_SHORT).show();
                    }
                });
            }
          }
        };

        thread.start();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.drawlisten.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draw2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        drawlisten.onOptionsItemSelected(item);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.drawlisten.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,planets.get(position),Toast.LENGTH_SHORT).show();
        getActionBar().setTitle(planets.get(position));
    }



    class Mydapter extends BaseAdapter{

        int[] draw={R.drawable.facebook,R.drawable.youtube,R.drawable.twitter,R.drawable.linkedin};
        @Override
        public int getCount() {
            return planets.size();
        }

        @Override
        public Object getItem(int position) {
            return planets.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null)
                convertView=getLayoutInflater().inflate(R.layout.custom,parent,false);
            ImageView icon=(ImageView)convertView.findViewById(R.id.imageView);
            TextView title=(TextView)convertView.findViewById(R.id.drawtext);
            title.setText(planets.get(position));
            icon.setImageResource(draw[position]);
            return convertView;
        }
    }
}



