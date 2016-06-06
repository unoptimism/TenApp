package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimism.tenapp.viewpager.VideoDuixiang;

public class TextShoucangZhanshi extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_aut;
    private TextView tv_aut2;
    private TextView tv_jianjie;
    private TextView tv_text;
    private TextView autbf;
    private ImageView iv_zhuanquan;


    private String author;
    private String text;
    private String title;
    private String authorbrief;
    private String  times;
    private String summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_shoucang_zhanshi);

        tv_title = (TextView) findViewById(R.id.text_title);
        tv_aut =(TextView) findViewById(R.id.text_aut);
        tv_aut2 = (TextView) findViewById(R.id.text_aut2);
        tv_jianjie = (TextView) findViewById(R.id.text_jianjie);
        tv_text = (TextView) findViewById(R.id.text_text);
        autbf = (TextView) findViewById(R.id.text_autbf);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        VideoDuixiang video = (VideoDuixiang) extras.get("Text");
        title = video.title;
        author = video.aut;
        text = video.text;
        authorbrief = video.autbf;
        times = video.times;
        summary = video.jianjie;



        tv_title.setText(title);
        tv_aut.setText(author+"| 阅读量："+times);
        tv_aut2.setText(author);
        tv_jianjie.setText(summary);
        tv_text.setText(text);
        autbf.setText(authorbrief);
        tv_jianjie.setText(summary);
    }
}
