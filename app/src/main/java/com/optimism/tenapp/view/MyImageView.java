package com.optimism.tenapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by optimism on 2016/6/3.
 */
public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
        init(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private ImageView mImageView;
    public void init(Context context, AttributeSet attributeSet) {

    }
}
