package com.optimism.tenapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GuanyuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void back(View view) {
        finish();
    }
}
