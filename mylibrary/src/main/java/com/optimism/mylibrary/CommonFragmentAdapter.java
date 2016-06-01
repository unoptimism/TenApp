package com.optimism.mylibrary;

import android.hardware.fingerprint.FingerprintManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by optimism on 2016/5/27.
 */
public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public CommonFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        int ret=0;
        if (mFragments != null) {
            ret = mFragments.size();
        }

        return ret;
    }
}
