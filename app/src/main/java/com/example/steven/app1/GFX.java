package com.example.steven.app1;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by steven on 20-4-15.
 */
public class GFX extends Activity {

    MyBringBack outVeiw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        outVeiw =new MyBringBack(getApplicationContext());
        setContentView(outVeiw);

    }


}
