package com.optimism.tenapp.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by optimism on 2016/6/3.
 */
public class MyTextView extends TextView {
    private BroadcastReceiver mBroadcastReceiver;

    private Context mContext;
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Text_ziti");
        mBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle bundle = intent.getExtras();
                String msg = bundle.getString("ziti");
                int size = Integer.parseInt(msg);
                setTextSize(size);

            }
        };
        context.registerReceiver(mBroadcastReceiver, intentFilter);



    }

    @Override
    protected void onDetachedFromWindow() {
        mContext.unregisterReceiver(mBroadcastReceiver);
        super.onDetachedFromWindow();
    }
}
