package com.example.steven.app1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by steven on 1-3-15.
 */
public class Splash extends Activity {
    private MediaPlayer song;
    @Override
    protected void onCreate(Bundle Stevencomeon) {
        super.onCreate(Stevencomeon);
        setContentView(R.layout.splash);


        song=MediaPlayer.create(this,R.raw.button);
        song.start();


        Thread timer=new Thread()
        {
            @Override
            public void run()
            {
                try{
                    sleep(3000);

                }catch (InterruptedException e)
                {
                    e.getStackTrace();

                }
                finally {

                    Intent start1=new Intent("com.example.steven.app1.menu");
                    startActivity(start1);

                }


            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.song.release();
        finish();
    }
}
