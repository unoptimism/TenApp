package com.optimism.tenapp;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class YijianActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijian);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YijianActivity.this, "已复制110至剪切板", Toast.LENGTH_SHORT).show();

            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YijianActivity.this, "已复制110至剪切板", Toast.LENGTH_SHORT).show();

            }
        });


    }



    public void back(View view) {
        finish();
    }
}
