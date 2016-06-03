package com.optimism.tenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ZitiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziti);
    }

    public void back(View view) {
     finish();

    }

    public void xiao(View view) {
        Intent intent = new Intent();
        intent.setAction("Text_ziti");
        intent.putExtra("ziti", "10");
        sendBroadcast(intent);

    }

    public void zhong(View view) {
        Intent intent = new Intent();
        intent.setAction("Text_ziti");
        intent.putExtra("ziti","15");
        sendBroadcast(intent);

    }


    public void da(View view) {

        Intent intent = new Intent();
        intent.setAction("Text_ziti");
        intent.putExtra("ziti","18");
        sendBroadcast(intent);
    }


}
