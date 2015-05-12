package com.example.steven.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by steven on 4-3-15.
 */
public class Data extends Activity implements View.OnClickListener{
    private EditText edit1;
    private Button b1;
    private Button b2;
    private TextView tvshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.relative);
        initial();
    }

    private void initial() {
        b1=(Button)findViewById(R.id.start1);
        b2=(Button)findViewById(R.id.start2);
        tvshow=(TextView)findViewById(R.id.textView1);
        edit1=(EditText)findViewById(R.id.edsend);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
           Bundle basket=data.getExtras();
            String a=basket.getString("key");
            tvshow.setText(a);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.start1)
        {
            String bread=this.edit1.getText().toString();
            Bundle basket=new Bundle();
            basket.putString("hungry",bread);
            basket.putInt("ID",911227);
            basket.putIntArray("numbers",new int[]{1,2,3,4});
            Intent a= new Intent(this,Openclass.class);
            a.putExtras(basket);


            startActivity(a);
        }

        else if (v.getId()==R.id.start2)
        {
            Intent i= new Intent(this, Openclass.class);
            i.putExtra("hungry","nopesteven");
            startActivityForResult(i,0);
        }

    }
}
