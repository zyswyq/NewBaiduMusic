package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class MainFragmentVPAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragments;


    public MainFragmentVPAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments!=null?fragments.get(position):null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
