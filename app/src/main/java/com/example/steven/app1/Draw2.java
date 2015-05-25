package com.example.steven.app1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        final Drawable background = listView.getBackground();
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
        this.bar2.setMax(200000);
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
                progress=0;

                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                       bar2.setProgress(msg.arg1);
                    }
                };
//                Thread t1=new Thread(new backgroud(),"pro");
//                t1.start();
//                dosomework1();
               Thread t1= new Thread(new mythread(),"download");
                t1.start();
//                dosomework2();
            }


        });


    }


    class backgroud implements Runnable{

        @Override
        public void run() {

            for(int i=0;i<=1000;i++){
                Message msg=Message.obtain();
                msg.arg1=i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
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
                        bar2.setVisibility(View.INVISIBLE);
                    }
                });
            }
          }
        };

        thread.start();
    }



    class mythread implements Runnable{

        @Override
        public void run() {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    bar2.setVisibility(View.VISIBLE);
                    Toast.makeText(Draw2.this, "start", Toast.LENGTH_SHORT).show();
                }
            });

            dosomework2();

        }
    }

    private void dosomework2(){
        HttpURLConnection connect=null;
        InputStream input=null;
        FileOutputStream out=null;

        try {
            URL downloadURL=new URL("http://www.parkeasier.com/wp-content/uploads/2015/05/android-for-wallpaper-8.png");
            connect=(HttpURLConnection)downloadURL.openConnection();

            Log.e("file size",String.valueOf(connect.getContentLength()));
            input=connect.getInputStream();

            File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()
                    +"/"+Uri.parse("http://www.parkeasier.com/wp-content/uploads/2015/05/android-for-wallpaper-8.png").getLastPathSegment());
            out=new FileOutputStream(file);

            byte[] buffer=new byte[1024];
            int flag;

            while ((flag=input.read(buffer))!=-1){
                out.write(buffer,0,flag);

                Message msg=Message.obtain();
                msg.arg1=progress;
                handler.sendMessage(msg);
//                bar2.setProgress(progress);
                progress=progress+(int)Math.round(200000*(1.0*flag/connect.getContentLength()));

                Log.v(Thread.currentThread().getName(),String.valueOf(Math.round(200000 * flag * 1.0 / connect.getContentLength())));
                Thread.sleep(100);
            }

            Log.v(Thread.currentThread().getName(),"success");
//            Toast.makeText(Draw2.this, "Finish", Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
           catch (IOException e) {
               e.printStackTrace();
           } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {


            /*
            * finally close the pipe and url connection
            * */
            if(connect!=null)
                connect.disconnect();
            if(input!=null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Draw2.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(Draw2.this, "finish", Toast.LENGTH_SHORT).show();
                    bar2.setVisibility(View.INVISIBLE);
                }
            });
        }
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



