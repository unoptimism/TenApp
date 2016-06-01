package com.optimism.tenapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.optimism.tenapp.MyAdapter;
import com.optimism.tenapp.R;
import com.optimism.tenapp.viewpager.VideoViewPagerFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements ViewPager.OnPageChangeListener, NetworkTaskCallback {

    private NetworkTask mNetworkTask1;
    private List<Integer> idList;
    private List<Fragment> mList;
    private ViewPager mViewPager;
    private ImageView iv1;
    private ImageView iv2;
    private int mDay;
    private int mMouth;
    private int week;
    private ImageView iv_mouth;
    private ImageView iv_week;
    private MyAdapter mMyAdapter;
    private int mWeeks;
    private int mDays=0;
    private Date mD;

    public VideoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mList = new ArrayList<>();
        idList = new ArrayList<>();
        mNetworkTask1 = new NetworkTask(getContext(), this);
        mNetworkTask1.execute("http://api.shigeten.net/api/Critic/GetCriticList");


        View view = inflater.inflate(R.layout.fragment_video, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_video);
        iv1 = (ImageView) view.findViewById(R.id.r1);
        iv2 = (ImageView) view.findViewById(R.id.r2);
        iv_mouth = (ImageView) view.findViewById(R.id.iv_mouth);
        iv_week = (ImageView) view.findViewById(R.id.iv_week);
        mMyAdapter = new MyAdapter(getChildFragmentManager(), mList);

        mViewPager.setAdapter(mMyAdapter);

        mD = new Date();

        getTime();

        changeDate();


        mMyAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(this);
        return view;
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mDays=position;
        getTime();




        changeDate();


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    @Override
    public void onTaskFinished(byte[] data) {


        try {

            String msg = new String(data, "UTF-8");
            JSONObject jj1 = new JSONObject(msg);
            JSONArray jj2 = jj1.optJSONArray("result");

            for (int i = 0; i < jj2.length(); i++) {
                JSONObject j = jj2.optJSONObject(i);
                int id = j.optInt("id");


                idList.add(id);


            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <idList.size(); i++) {
            Fragment f = new VideoViewPagerFragment(idList.get(i));
            mList.add(f);

        }
        mMyAdapter.notifyDataSetChanged();


    }


    private void changeDate() {




        switch (week) {

            case 2:
                iv_week.setImageResource(R.mipmap.week_1);
                break;
            case 3:
                iv_week.setImageResource(R.mipmap.week_2);

                break;
            case 4:
                iv_week.setImageResource(R.mipmap.week_3);
                break;
            case 5:
                iv_week.setImageResource(R.mipmap.week_4);

                break;
            case 6:
                iv_week.setImageResource(R.mipmap.week_5);

                break;
            case 7:
                iv_week.setImageResource(R.mipmap.week_6);

                break;
            case 1:
                iv_week.setImageResource(R.mipmap.week_7);

                break;
        }


        switch (mMouth) {

            case 1:
                iv_mouth.setImageResource(R.mipmap.month_1);

                break;
            case 2:
                iv_mouth.setImageResource(R.mipmap.month_2);
                break;
            case 3:
                iv_mouth.setImageResource(R.mipmap.month_3);
                break;
            case 4:
                iv_mouth.setImageResource(R.mipmap.month_4);
                break;
            case 5:
                iv_mouth.setImageResource(R.mipmap.month_5);
                break;
            case 6:
                iv_mouth.setImageResource(R.mipmap.month_6);
                break;
            case 7:
                iv_mouth.setImageResource(R.mipmap.month_7);
                break;
            case 8:
                iv_mouth.setImageResource(R.mipmap.month_8);
                break;
            case 9:
                iv_mouth.setImageResource(R.mipmap.month_9);
                break;
            case 10:
                iv_mouth.setImageResource(R.mipmap.month_10);
                break;
            case 11:
                iv_mouth.setImageResource(R.mipmap.month_11);
                break;
            case 12:
                iv_mouth.setImageResource(R.mipmap.month_12);
                break;


        }



        switch (mDay % 10) {
            case 0:
                iv2.setImageResource(R.mipmap.date_0);
                break;
            case 1:
                iv2.setImageResource(R.mipmap.date_1);
                break;
            case 2:
                iv2.setImageResource(R.mipmap.date_2);
                break;
            case 3:
                iv2.setImageResource(R.mipmap.date_3);
                break;
            case 4:
                iv2.setImageResource(R.mipmap.date_4);
                break;
            case 5:
                iv2.setImageResource(R.mipmap.date_5);
                break;
            case 6:
                iv2.setImageResource(R.mipmap.date_6);
                break;
            case 7:
                iv2.setImageResource(R.mipmap.date_7);
                break;
            case 8:
                iv2.setImageResource(R.mipmap.date_8);
                break;
            case 9:
                iv2.setImageResource(R.mipmap.date_9);
                break;
        }

        switch (mDay / 10) {
            case 0:
                iv1.setImageResource(R.mipmap.date_0);
                break;
            case 1:
                iv1.setImageResource(R.mipmap.date_1);
                break;
            case 2:
                iv1.setImageResource(R.mipmap.date_2);
                break;
            case 3:
                iv1.setImageResource(R.mipmap.date_3);
                break;


        }

    }


    public void getTime (){

        Calendar c = Calendar.getInstance();
        c.setTime(mD);

        c.add(Calendar.DAY_OF_YEAR,-mDays);

        mWeeks = c.get(Calendar.DAY_OF_WEEK);

        mDay =c.get(Calendar.DATE);
        mMouth = c.get(Calendar.MONTH)+1;
        week = mWeeks;

    }




}
