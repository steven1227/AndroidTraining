package com.example.steven.app1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by steven on 20-4-15.
 */
public class Surface extends Activity implements View.OnTouchListener{

    MyBringBackSurface surfaceview;
    float x,y,sX,sY,fX,fY;
    float dX,dY,aniX,aniY;
    float scaledX=0;
    float scaledY=0;
    Bitmap test;
    Bitmap background;
    Bitmap plus;
    SoundPool sp;
    int clickid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes clicksound = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();
            sp = new SoundPool.Builder().setMaxStreams(10).setAudioAttributes(clicksound).build();
            this.clickid = sp.load(this, R.raw.hello2, 1);
        }else {
            sp=new SoundPool(10, AudioManager.STREAM_MUSIC,1);
            clickid=sp.load(this,R.raw.hello2,1);
        }

        surfaceview = new MyBringBackSurface(this);
        surfaceview.setOnTouchListener(this);
        test=BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        background= BitmapFactory.decodeResource(getResources(), R.drawable.background);
        plus=BitmapFactory.decodeResource(getResources(),R.drawable.pos);
        x=0;
        y=0;
        sX=0;
        sY=0;
        fX=0;
        fY=0;
        dX=dY=0;
        aniX=aniY=0;
        setContentView(this.surfaceview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        surfaceview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceview.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        /*
            FPS , to lower the FPS, use sleep. Maybe not a good option;
         */
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x=event.getX();
        y=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            {
                this.sp.play(this.clickid,1,1,1,0,1);
                dX=0;
                dY=0;
                fX=0;
                fY=0;
                aniX=0;
                aniY=0;
                scaledX=0;
                scaledY=0;
                sX=event.getX();
                sY=event.getY();

                break;
            }
            case MotionEvent.ACTION_UP:
            {
                this.sp.play(this.clickid,1,1,1,0,1);
                fX=event.getX();
                fY=event.getY();
                dX=fX-sX;
                dY=fX-sY;
                scaledX=dX/30;
                scaledY=dY/30;
                x=0;
                y=0;

                break;
            }




        }
        Log.e( this.getClass().getSimpleName(),"x:  "+x+"----"+"y:  "+y);
        return true;
    }




      class MyBringBackSurface  extends SurfaceView implements Runnable{

        SurfaceHolder ourHolder;
        Thread myThread=null;
        Boolean isrun=false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder=this.getHolder();

        }


        public void run() {
            while(isrun)
            {
                if(!ourHolder.getSurface().isValid())
                {
                    continue;
                }
                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawBitmap(background,0,0,null);
//                canvas.drawRGB(21,241,120);
                if( x!=0 && y!=0){

                    canvas.drawBitmap(test, x-test.getWidth()/2,y-test.getHeight()/2, null);
                }
                if( sX!=0 && sY!=0){

                    canvas.drawBitmap(plus, sX-test.getWidth()/2,sY-test.getHeight()/2, null);
                }
                if( fX!=0 && fY!=0){

                    canvas.drawBitmap(test, fX-test.getWidth()/2-aniX,fY-test.getHeight()/2-aniY, null);
                    canvas.drawBitmap(plus, fX-test.getWidth()/2,fY-test.getHeight()/2, null);

                }
                aniX=aniX+scaledX;
                aniY=aniY+scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            this.isrun=false;
            while (true) {
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            myThread=null;

        }
        public void resume(){
            this.isrun=true;
            myThread=new Thread(this,"Hello");
            myThread.start();

        }
    }

}
