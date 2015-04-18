package com.example.steven.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by steven on 4-3-15.
 */
public class Openclass extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private TextView hi;
    private TextView returntext;
    private Button return1;
    private RadioGroup group;
    private String bread;
    private String sendData="Probably Right";;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.send);

        hi=(TextView)findViewById(R.id.textView);
        return1 = (Button) findViewById(R.id.buttonreturn);
        group=(RadioGroup)findViewById(R.id.plaugroup);
        return1.setOnClickListener(this);
        group.setOnCheckedChangeListener(this);
        returntext=(TextView)findViewById(R.id.textreturn);

        Intent a=getIntent();

        Bundle gotbasket=a.getExtras();
        //gotbasket.get
        Log.v("do not worry",""+a.hasExtra("hungry1"));
 //       bread=gotbasket.getString("hungry");
        hi.setText(a.getStringExtra("hungry")+":"+gotbasket.getInt("ID")+gotbasket.getIntArray("numbers")[1]);

    }

    @Override
    public void onClick(View v) {
        Intent person =new Intent();
        Bundle backpack=new Bundle();
        backpack.putString("key",this.sendData);
        person.putExtras(backpack);
        setResult(RESULT_OK,person);
        finish();

    }




    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId)
        {
            case R.id.play1:
               sendData="Probably Right";
                break;
            case R.id.play2:
                sendData="Definitely right";
                break;
            case R.id.play3:
                sendData="go on";
                break;

        }
        returntext.setText(this.sendData);
    }
}
