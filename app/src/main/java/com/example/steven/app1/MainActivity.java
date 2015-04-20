package com.example.steven.app1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

   private int counter=0;

   private Button add;//=(Button)findViewById(R.id.b_add);
   private Button sub;
   private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        add = (Button) findViewById(R.id.b_add);
        display = (TextView) findViewById(R.id.tvdisplay);
        sub = (Button) findViewById(R.id.b_sub);

            add.setOnClickListener(this);
            sub.setOnClickListener(this);

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                counter++;
//                display.setText(" your total is " + counter);
//            }
//        });

//        sub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                counter--;
//                display.setText(" your total is " + counter);
//
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.aboutus) {
           Intent i=new Intent(this,AboutUs.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b_add){
            this.counter++;

        }
        else if(v.getId()==R.id.b_sub){
            this.counter--;

        }

        this.display.setText(" your total is " + counter);

    }
    public void SendMsg(View v){

        Toast.makeText(getApplicationContext(),"Hello World",Toast.LENGTH_SHORT).show();

    }
}
