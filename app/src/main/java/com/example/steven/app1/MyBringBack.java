package com.example.steven.app1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by steven on 20-4-15.
 */
public class MyBringBack extends View {


    private Bitmap gBall;
    float changeY;
    Typeface font;
    public MyBringBack(Context context) {
        super(context);
        this.changeY=0;
        this.gBall= BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        this.font=Typeface.createFromAsset(context.getAssets(),"G-Unit.TTF");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);


        Rect middleRect=new Rect();
        middleRect.set(0,400,canvas.getWidth(),550);

        Paint text= new Paint();
        text.setARGB(50,55,23,23);
        text.setTypeface(font);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(50);
        canvas.drawText("My first ball",canvas.getHeight()/2,canvas.getWidth()/2,text);



        Paint ourblue=new Paint();
        ourblue.setColor(Color.BLUE);
        canvas.drawRect(middleRect,ourblue);

        canvas.drawBitmap(this.gBall, canvas.getWidth()/2-this.gBall.getWidth()/2, changeY, null);
        if( changeY<=canvas.getHeight())
            changeY=changeY+5;
        else
            changeY=0;

        invalidate();
    }

}
