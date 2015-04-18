package com.example.steven.app1;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by steven on 3-3-15.
 */
public class Testplay extends Activity implements View.OnClickListener{
    private Button check;
    private TextView resultplay;
    private ToggleButton pass;
    private EditText input;

    private void banking()
    {
        setContentView(R.layout.text);
        this.check=(Button)findViewById(R.id.bresult);
        this.pass=(ToggleButton)findViewById(R.id.toggleButton);
        this.input=(EditText)findViewById(R.id.commands);
        this.resultplay=(TextView)findViewById(R.id.tvresults);
        pass.setOnClickListener(this);
        check.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.banking();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case (R.id.bresult):
            {
                String checkable=input.getText().toString();
                resultplay.setText(checkable);
                if(checkable.equals("left"))
                {
                    resultplay.setGravity(Gravity.LEFT);
                }
                else if(checkable.equals("right"))
                {
                    resultplay.setGravity(Gravity.RIGHT);
                }
                else if(checkable.equals("blue"))
                {
                    resultplay.setTextColor(Color.BLUE);
                }
                else if (checkable.equals("WTF"))
                {
                    Random crazy=new Random();
                    resultplay.setText("what");
                    resultplay.setTextSize(crazy.nextInt(75));
                    resultplay.setTextColor(Color.rgb(crazy.nextInt(255),crazy.nextInt(255),crazy.nextInt(255)));
                    switch (crazy.nextInt(3))
                    {
                        case 0:resultplay.setGravity(Gravity.CENTER);break;
                        case 1:resultplay.setGravity(Gravity.LEFT);break;
                        case 2:resultplay.setGravity(Gravity.RIGHT);break;
                    }
                }
                else
                {
                    resultplay.setGravity(Gravity.CENTER);
                    resultplay.setText("invalid");
                    resultplay.setTextColor(Color.BLACK);
                }


            }
            break;




            case (R.id.toggleButton):
            {
                if(pass.isChecked())
                {
                    input.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
                else{


                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }


            }
           break;


        }

    }
}

// in Java listener programming, it has two methods to implement the listener. One is do the new anonymous ,
// two is make this class implements the onclicklistener and than set(this)


//       pass.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               if(pass.isChecked())
//               {
//                   input.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
//
//               }
//               else{
//
//
//                   input.setInputType(InputType.TYPE_CLASS_TEXT);
//               }
//
//           }
//       });
//
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String checkable=input.getText().toString();
//                resultplay.setText(checkable);
//                if(checkable.equals("left"))
//                {
//                    resultplay.setGravity(Gravity.LEFT);
//                }
//                else if(checkable.equals("right"))
//                {
//                    resultplay.setGravity(Gravity.RIGHT);
//                }
//                else if(checkable.equals("blue"))
//                {
//                    resultplay.setTextColor(Color.BLUE);
//                }
//                else if (checkable.equals("WTF"))
//                {
//                    Random crazy=new Random();
//                    resultplay.setText("what");
//                    resultplay.setTextSize(crazy.nextInt(75));
//                    resultplay.setTextColor(Color.rgb(crazy.nextInt(255),crazy.nextInt(255),crazy.nextInt(255)));
//                    switch (crazy.nextInt(3))
//                    {
//                        case 0:resultplay.setGravity(Gravity.CENTER);break;
//                        case 1:resultplay.setGravity(Gravity.LEFT);break;
//                        case 2:resultplay.setGravity(Gravity.RIGHT);break;
//                    }
//                }
//                else
//                {
//                    resultplay.setGravity(Gravity.CENTER);
//                    resultplay.setText("invalid");
//                    resultplay.setTextColor(Color.BLACK);
//                }
//
//
//            }
//        });