package com.optimism.tenapp;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.optimism.tenapp.fragment.ImageFragment;
import com.optimism.tenapp.fragment.PersonFragment;
import com.optimism.tenapp.fragment.TextFragment;
import com.optimism.tenapp.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    public static RadioGroup rg;

    private List<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


         rg = (RadioGroup) findViewById(R.id.rg);


        mFragments = new ArrayList<>();


        if (savedInstanceState == null) {//第一次创建
            Fragment fragment = new VideoFragment();
            mFragments.add(fragment);

            fragment =  new TextFragment();
            mFragments.add(fragment);
            fragment = new ImageFragment();
            mFragments.add(fragment);
            fragment = new PersonFragment();
            mFragments.add(fragment);




            FragmentTransaction transaction = manager.beginTransaction();


            int index=0;
            for (Fragment f : mFragments) {
//                第三个参数相当于给fragment
//                设置tag 之后可以进行查找
                transaction.add(R.id.fragment_con, f,"tag"+index);
                transaction.hide(f);
                index++;
            }
//          第一个显示
            transaction.show(mFragments.get(0));
            transaction.commit();
        }
        else    //不是第一次创建, fragment会自动的由activity创建
        {
//            fragmentManager的管理


            for (int i = 0; i < 4; i++) {
                String tag="tag"+i;
                //            根据add的时候 设置的tag来查找fragment
                Fragment f = manager.findFragmentByTag(tag);
                if (f != null) {
                    mFragments.add(f);
                }
            }


        }



        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;

        switch (checkedId) {
            case R.id.rb1:
                index=0;
                 break;
            case R.id.rb2:
                index=1;
                break;
            case R.id.rb3:
                index=2;
                break;
            case R.id.rb4:
                index=3;
                break;

        }

        checkedRB(index);

    }

    private void checkedRB(int index) {
        if (index >= 0 && index < mFragments.size()) {


            int size = mFragments.size();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            for (int i = 0; i < size; i++) {
                Fragment f = mFragments.get(i);

                if (i == index) {
//                    显示
                    transaction.show(f);

                } else {
//                    隐藏
                    transaction.hide(f);
                }

            }

            transaction.commit();
        }


    }






    private long lastTime;

    @Override
    public void onBackPressed() {
        long ct=System.currentTimeMillis();

        if (ct-lastTime>2000)
        {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            lastTime=ct;
        }
        else
        {
            super.onBackPressed();
        }


    }





    /**
     * 回调接口
     * @author zhaoxin5
     *
     */
    public interface MyTouchListener
    {
         void onTouchEvent(MotionEvent event);
    }

    /*
     * 保存MyTouchListener接口的列表
     */
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener)
    {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener)
    {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

}
