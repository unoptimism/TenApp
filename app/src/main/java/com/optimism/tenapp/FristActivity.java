package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FristActivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        iv = (ImageView) findViewById(R.id.iv);

        iv.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_go);
        iv.startAnimation(animation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        MyThread myThread = new MyThread();
        myThread.start();
    }


    private class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent it=new Intent(FristActivity.this,MainActivity.class);
            startActivity(it);

            finish();
        }
    }


}
