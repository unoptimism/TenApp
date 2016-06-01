package com.optimism.tenapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by optimism on 2016/5/30.
 */
public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public MyAdapter(FragmentManager manager,List<Fragment> mList) {

        super(manager);

        this.mList = mList;

    }



    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        int ret=0;
        if (mList != null) {
            ret = mList.size();
        }

        return ret;
    }
}
