package com.example.steven.app1;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by steven on 3-3-15.
 */
public class Camera extends Activity implements View.OnClickListener{
    private ImageButton ib;
    private Button b;
    private ImageView iv;
    private Intent in;
    private final static int cameraData=0;
    private Bitmap bip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initial();
    }

    private void initial() {
        iv=(ImageView)findViewById(R.id.imgpic);
        b=(Button)findViewById(R.id.imgbutton);
        ib=(ImageButton)findViewById(R.id.ibutton);
        ib.setOnClickListener(this);
        b.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle extras=data.getExtras();
            bip=(Bitmap)extras.get("data");
            iv.setImageBitmap(bip);

        }
    }

    @Override
    public void onClick(View v) {
       int i=v.getId();
        if(i==R.id.imgbutton)
        {
           WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            try {
                wallpaperManager.setBitmap(bip);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (i==R.id.ibutton)
        {
            in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(in,cameraData);

        }
    }
}
