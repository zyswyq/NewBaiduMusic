package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class MusicFragmentVPAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragments;
    private Context context;

    public MusicFragmentVPAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
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
