package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private ImageView iv;
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




    }
}
