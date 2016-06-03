package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private ImageView iv;
    private float mX=0;
    private float mY=0;
    private float mMoveX=0;
    private float mMoveY=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        iv = (ImageView) findViewById(R.id.iv);
        Intent it = getIntent();
        String ivUrl = it.getStringExtra("ivUrl");
        Picasso.with(this).load("http://api.shigeten.net/" + ivUrl).into(iv);


        iv.setOnTouchListener(new View.OnTouchListener() {
            float lastX;
            float lastY;
            int i;
            float xxx;
            float yyy;
            float kuan;
            float gao;
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getX();
                        lastY = event.getY();
                        i = 0;
                        Log.d("I", i + "");

                        break;

                    case MotionEvent.ACTION_MOVE:
                        if (event.getPointerCount() >= 2) {
                           kuan = event.getX(0) - event.getX(1);
                            gao= event.getY(0) - event.getY(1);
                            if (i == 0) {
                                xxx = kuan;
                                yyy = gao;
                                Log.d("I", i + "");
                                Log.d("XX:", xxx + "");
                                Log.d("YY:", yyy+"");
                                i++;
                            } else {

                                if (mX == 0 && mY == 0) {
                                    mX = (kuan / xxx);
                                    mY = (gao / yyy);
                                } else {
                                    mX = (kuan / xxx) * mX;
                                    mY = (gao / yyy) * mY;
                                }
                                imageScale();
                                Log.d("I", i + "");
                                Log.d("XX:", xxx + "");
                                Log.d("YY:", yyy + "");
                            }
                        }
                        else if (event.getPointerCount() == 1) {
                            mMoveX = event.getX()- lastX;
                            mMoveY = event.getY()- lastY;
                            imageMove();
                        }
                        break;

                }

                return true;
            }
        });


    }

    private void imageScale() {
        iv.setScaleX(mX);
        iv.setScaleY(mY);

    }


    private void imageMove() {
        float x = iv.getX();
        float y = iv.getY();
        iv.setX(x+mMoveX);
        iv.setY(y+mMoveY);
    }
}
