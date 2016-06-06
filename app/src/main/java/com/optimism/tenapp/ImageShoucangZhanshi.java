package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimism.tenapp.viewpager.VideoDuixiang;
import com.squareup.picasso.Picasso;

public class ImageShoucangZhanshi extends AppCompatActivity {

    private ImageView iv;
    private TextView tv_title;
    private TextView tv_aut;
    private TextView mTextView;

    private ImageView iv_zhuanquan;
    private String ivUrl;
    private String title;
    private String aut;
    private String text1;
    private String mText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_shoucang_zhanshi);

        iv = (ImageView) findViewById(R.id.iv);
        tv_title = (TextView) findViewById(R.id.image_title);
        tv_aut = (TextView) findViewById(R.id.image_aut);
        mTextView = (TextView) findViewById(R.id.image_text);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        VideoDuixiang video = (VideoDuixiang) extras.get("Image");

        title = video.title;
        aut = video.aut;
        text1 = video.text1;
        mText2 = video.text2;
        ivUrl = video.image1;




        Picasso.with(this).load("http://api.shigeten.net/"+ivUrl).into(iv);
        tv_title.setText(title);
        tv_aut.setText(aut);
        mTextView.setText(text1+"\r\n\r\n"+mText2);
    }
}
