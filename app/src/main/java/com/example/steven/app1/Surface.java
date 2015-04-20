package com.example.steven.app1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        surfaceview = new MyBringBackSurface(this);
        surfaceview.setOnTouchListener(this);
        x=0;
        y=0;
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
        x=event.getX();
        y=event.getY();
        Log.e( this.getClass().getSimpleName(),"x:  "+x+"----"+"y:  "+y);
        return false;
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
                canvas.drawRGB(21,241,120);
                if( x!=0 && y!=0){
                    Bitmap test= BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
                    canvas.drawBitmap(test, x,y, null);
                }
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
