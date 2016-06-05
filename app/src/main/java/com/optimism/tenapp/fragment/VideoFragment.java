package com.optimism.tenapp.fragment;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.optimism.tenapp.MainActivity;
import com.optimism.tenapp.MyAdapter;
import com.optimism.tenapp.R;
import com.optimism.tenapp.viewpager.BlankFragment;
import com.optimism.tenapp.viewpager.VideoViewPagerFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
    private static ImageView iv_anniu;
    private float downY;
    private float moveY;
    private ImageView anniu;

    private MainActivity.MyTouchListener mTouchListener = new MainActivity.MyTouchListener() {
        @Override
        public void onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveY = event.getY();

                    if (downY > moveY+20) {
                        iv_anniu.setVisibility(View.INVISIBLE);
                        MainActivity.rg.setVisibility(View.INVISIBLE);
                    } else {
                        MainActivity.rg.setVisibility(View.VISIBLE);
                        iv_anniu.setVisibility(View.VISIBLE);
                    }
                    break;


            }


        }
    };
    private NetworkTask networkTask2;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //在该Fragment的构造函数中注册mTouchListener的回调
        if(mTouchListener!=null){
            if(getActivity()!=null){
                ((MainActivity)getActivity()).registerMyTouchListener(mTouchListener);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mTouchListener!=null){
            if(getActivity()!=null){
                ((MainActivity)getActivity()).registerMyTouchListener(mTouchListener);
            }
        }
    }

    public VideoFragment() {

    }

    private SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mList = new ArrayList<>();
        idList = new ArrayList<>();
        mNetworkTask1 = new NetworkTask(getContext(), this);
        mNetworkTask1.execute("http://api.shigeten.net/api/Critic/GetCriticList");
        String path = getActivity().getCacheDir().getAbsolutePath() + File.separator + "MyDown.db";// 数据库文件的绝对路径
        db = SQLiteDatabase.openOrCreateDatabase(path, null);




        View view = inflater.inflate(R.layout.fragment_video, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_video);
        iv1 = (ImageView) view.findViewById(R.id.r1);
        iv2 = (ImageView) view.findViewById(R.id.r2);
        iv_mouth = (ImageView) view.findViewById(R.id.iv_mouth);
        iv_week = (ImageView) view.findViewById(R.id.iv_week);
        iv_anniu = (ImageView) view.findViewById(R.id.anniu);
        mMyAdapter = new MyAdapter(getChildFragmentManager(), mList);

        mViewPager.setAdapter(mMyAdapter);

        mD = new Date();

        getTime();

        changeDate();


        mMyAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(this);


        anniu = (ImageView) view.findViewById(R.id.anniu);
        anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.execSQL("create table if not exists Video(_id integer primary key autoincrement,"+
                        "id integer," +
                        "publishtime varchar," +
                        "shoucangTitle varchar," +
                        "shoucangAut varchar," +
                        "shoucangAutbf varchar," +
                        "shoucangTimes varchar,"+
                        "shoucangText1 varchar," +
                        "shoucangText2 varchar," +
                        "shoucangText3 varchar," +
                        "shoucangText4 varchar," +
                        "shoucangText5 varchar," +
                        "shoucangRealtitle varchar,"+
                        "shoucangImage1 varchar," +
                        "shoucangImage2 varchar," +
                        "shoucangImage3 varchar," +
                        "shoucangImage4 varchar," +
                        "shoucangImage5 varchar" +
                        ")");


                Toast.makeText(getActivity(), "收藏成功!", Toast.LENGTH_SHORT).show();

                int currentItem = mViewPager.getCurrentItem();
                Integer shoucangID = idList.get(currentItem -1);
                if (shoucangID != 0) {

                    networkTask2 = new NetworkTask(getContext(), new NetworkTaskCallback() {
                        @Override
                        public void onTaskFinished(byte[] data) {
                            try {

                                String msg = new String(data, "UTF-8");
                                JSONObject jsonObject = new JSONObject(msg);
                                String id = jsonObject.getString("id");
                                String publishtime =jsonObject.getString("publishtime");
                                String shoucangTitle = jsonObject.getString("title");
                                String shoucangAut = jsonObject.getString("author");
                                String shoucangAutbf = jsonObject.getString("authorbrief");
                                String shoucangTimes = jsonObject.getInt("times")+"";
                                String shoucangText1 = jsonObject.getString("text1");
                                String shoucangText2 = jsonObject.getString("text2");
                                String shoucangText3 = jsonObject.getString("text3");
                                String shoucangText4 = jsonObject.getString("text4");
                                String shoucangText5 = jsonObject.getString("text5");
                                String shoucangRealtitle = jsonObject.getString("realtitle");
                                String shoucangImage1="http://api.shigeten.net/"+jsonObject.getString("image1");
                                String shoucangImage2="http://api.shigeten.net/"+jsonObject.getString("image2");
                                String shoucangImage3="http://api.shigeten.net/"+jsonObject.getString("image3");
                                String shoucangImage4="http://api.shigeten.net/"+jsonObject.getString("image4");
                                String shoucangImage5="http://api.shigeten.net/"+jsonObject.getString("imageforplay");



                                db.execSQL("insert into video (id,publishtime,shoucangTitle,shoucangAut,shoucangAutbf,shoucangTimes,shoucangText1,shoucangText2,shoucangText3,shoucangText4,shoucangText5,shoucangRealtitle,shoucangImage1,shoucangImage2,shoucangImage3,shoucangImage4,shoucangImage5) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", // ?是占位符
                                        new Object[]{id, publishtime, shoucangTitle, shoucangAut, shoucangAutbf, shoucangTimes, shoucangText1, shoucangText2, shoucangText3, shoucangText4, shoucangText5, shoucangRealtitle, shoucangImage1, shoucangImage2, shoucangImage3, shoucangImage4, shoucangImage5});


                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    networkTask2.execute("http://api.shigeten.net/api/Critic/GetCriticContent?id=" + shoucangID);


                }
            }
        });


        return view;
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected( int position) {

        mDays=position;
        getTime();

        if (position == 0) {
            mViewPager.setCurrentItem(1);
            Toast.makeText(getActivity(), "已经是最新内容", Toast.LENGTH_SHORT).show();
        }
        else if (position == mList.size()-1) {
            mViewPager.setCurrentItem(mList.size()-2);
            Toast.makeText(getActivity(), "已经到末页", Toast.LENGTH_SHORT).show();

        }
        changeDate();



    }

    @Override
    public void onPageScrollStateChanged( int state) {



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

        mList.add(new BlankFragment());
        for (int i = 0; i <idList.size(); i++) {
            Fragment f = new VideoViewPagerFragment(idList.get(i));
            mList.add(f);

        }
        mList.add(new BlankFragment());
        mMyAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(1);

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
        c.add(Calendar.DAY_OF_YEAR, 1);
        c.add(Calendar.DAY_OF_YEAR,-mDays);

        mWeeks = c.get(Calendar.DAY_OF_WEEK);

        mDay =c.get(Calendar.DATE);
        mMouth = c.get(Calendar.MONTH)+1;
        week = mWeeks;

    }





}
