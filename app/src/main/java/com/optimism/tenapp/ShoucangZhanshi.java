package com.optimism.tenapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.tenapp.viewpager.VideoDuixiang;
import com.squareup.picasso.Picasso;

public class ShoucangZhanshi extends AppCompatActivity {


    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;


    private ImageView iv_zhuanquan;

    private int id;
    private NetworkTask networkTask;
    private String mTitle;
    private String mAuthor;
    private String mAuthorbrief;
    private String mTimes;
    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;
    private String mText5;
    private String mRealtitle;
    private String mImageUrl1;
    private String mImageUrl2;
    private String mImageUrl3;
    private String mImageUrl4;
    private String mImageUrl5;
    private String mPushlistime;
    private AnimationDrawable mAnimationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang_zhanshi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tv1 = (TextView) findViewById(R.id.vido_title);
        tv2 = (TextView) findViewById(R.id.vido_author);
        tv3 = (TextView) findViewById(R.id.video_authorbrief);
        tv4 = (TextView) findViewById(R.id.video_text1);
        tv5 = (TextView) findViewById(R.id.video_text2);
        tv6 = (TextView) findViewById(R.id.video_text3);
        tv7 = (TextView) findViewById(R.id.video_text4);
        tv8 = (TextView) findViewById(R.id.video_text5);
        tv9 = (TextView) findViewById(R.id.video_realtitle);
        tv10 = (TextView) findViewById(R.id.video_author2);

        iv1 = (ImageView) findViewById(R.id.video_iv1);
        iv2 = (ImageView) findViewById(R.id.video_iv2);
        iv3 = (ImageView) findViewById(R.id.video_iv3);
        iv4 = (ImageView) findViewById(R.id.video_iv4);
        iv5 = (ImageView) findViewById(R.id.video_iv5);



        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        VideoDuixiang video = (VideoDuixiang) extras.get("Video");

        Log.d("video", video.aut);

        mTitle = video.title.toString();
        mAuthor = video.aut.toString();
        mAuthorbrief = video.autbf;
        mTimes = video.times;
        mText1 = video.text1;
        mText2 = video.text2;
        mText3 = video.text3;
        mText4 = video.text4;
        mText5 = video.text5;
        mRealtitle = video.realtitle;
        mImageUrl1 = video.image1;
        mImageUrl2 = video.image2;
        mImageUrl3 = video.image3;
        mImageUrl4 = video.image4;
        mImageUrl5 = video.image5;
        mPushlistime = video.publishtime;

        tv1.setText(mTitle);
        tv2.setText(mAuthor+" | 阅读量："+mTimes);
        tv3.setText(mAuthorbrief);
        tv4.setText(mText1);
        tv5.setText(mText2);
        tv6.setText(mText3);
        tv7.setText(mText4);
        tv8.setText(mText5);
        tv9.setText(mRealtitle);
        tv10.setText(mAuthor);
        Picasso.with(this).load(mImageUrl5).into(iv1);
        Picasso.with(this).load(mImageUrl1).into(iv2);
        Picasso.with(this).load(mImageUrl2).into(iv3);
        Picasso.with(this).load(mImageUrl3).into(iv4);
        Picasso.with(this).load(mImageUrl4).into(iv5);

    }
}
