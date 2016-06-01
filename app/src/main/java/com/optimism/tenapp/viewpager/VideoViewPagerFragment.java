package com.optimism.tenapp.viewpager;


import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.optimism.tenapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoViewPagerFragment extends Fragment {
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

    private int id=0;
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
    private AnimationDrawable mAnimationDrawable;



    public VideoViewPagerFragment() {

    }



    public VideoViewPagerFragment(int id) {
        this.id = id;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_view_pager, container, false);

        iv_zhuanquan = (ImageView)view.findViewById(R.id.iv_zhuanquan);
        Drawable drawable = iv_zhuanquan.getDrawable();
        mAnimationDrawable = (AnimationDrawable) drawable;
        mAnimationDrawable.start();


        tv1 = (TextView) view.findViewById(R.id.vido_title);
        tv2 = (TextView) view.findViewById(R.id.vido_author);
        tv3 = (TextView) view.findViewById(R.id.video_authorbrief);
        tv4 = (TextView) view.findViewById(R.id.video_text1);
        tv5 = (TextView) view.findViewById(R.id.video_text2);
        tv6 = (TextView) view.findViewById(R.id.video_text3);
        tv7 = (TextView) view.findViewById(R.id.video_text4);
        tv8 = (TextView) view.findViewById(R.id.video_text5);
        tv9 = (TextView) view.findViewById(R.id.video_realtitle);
        tv10 = (TextView) view.findViewById(R.id.video_author2);

        iv1 = (ImageView) view.findViewById(R.id.video_iv1);
        iv2 = (ImageView) view.findViewById(R.id.video_iv2);
        iv3 = (ImageView) view.findViewById(R.id.video_iv3);
        iv4 = (ImageView) view.findViewById(R.id.video_iv4);
        iv5 = (ImageView) view.findViewById(R.id.video_iv5);


        if (id != 0) {

            networkTask = new NetworkTask(getContext(), new NetworkTaskCallback() {
                @Override
                public void onTaskFinished(byte[] data) {
                    try {

                        String msg = new String(data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(msg);
                        mTitle = jsonObject.getString("title");
                        mAuthor = jsonObject.getString("author");
                        mAuthorbrief = jsonObject.getString("authorbrief");
                        mTimes = jsonObject.getInt("times")+"";
                        mText1 = jsonObject.getString("text1");
                        mText2 = jsonObject.getString("text2");
                        mText3 = jsonObject.getString("text3");
                        mText4 = jsonObject.getString("text4");
                        mText5 = jsonObject.getString("text5");
                        mRealtitle = jsonObject.getString("realtitle");

                        mImageUrl1="http://api.shigeten.net/"+jsonObject.getString("image1");
                        mImageUrl2="http://api.shigeten.net/"+jsonObject.getString("image2");
                        mImageUrl3="http://api.shigeten.net/"+jsonObject.getString("image3");
                        mImageUrl4="http://api.shigeten.net/"+jsonObject.getString("image4");
                        mImageUrl5="http://api.shigeten.net/"+jsonObject.getString("imageforplay");


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


                        Picasso.with(getContext()).load(mImageUrl5).into(iv1);
                        Picasso.with(getContext()).load(mImageUrl1).into(iv2);
                        Picasso.with(getContext()).load(mImageUrl2).into(iv3);
                        Picasso.with(getContext()).load(mImageUrl3).into(iv4);
                        Picasso.with(getContext()).load(mImageUrl4).into(iv5);
                        iv_zhuanquan.setVisibility(View.INVISIBLE);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            networkTask.execute("http://api.shigeten.net/api/Critic/GetCriticContent?id=" + id);


        }






        return view;
    }


}
