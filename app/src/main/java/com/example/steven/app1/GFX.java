package com.example.steven.app1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by steven on 20-4-15.
 */
public class GFX extends Activity {

    MyBringBack outVeiw;
    PowerManager.WakeLock wl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
         wl=pm.newWakeLock(PowerManager.RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY,"hi");

        super.onCreate(savedInstanceState);
        wl.acquire();
        outVeiw =new MyBringBack(getApplicationContext());
        setContentView(outVeiw);

    }

    @Override
    protected void onResume() {
        super.onResume();
        wl.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
