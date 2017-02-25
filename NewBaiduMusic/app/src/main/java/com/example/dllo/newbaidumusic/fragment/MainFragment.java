package com.example.dllo.newbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.MainFragmentVPAdapter;
import com.example.dllo.newbaidumusic.fragment.mainfragment.DynamicFragment;
import com.example.dllo.newbaidumusic.fragment.mainfragment.FindFragment;
import com.example.dllo.newbaidumusic.fragment.mainfragment.MusicFragment;
import com.example.dllo.newbaidumusic.fragment.mainfragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/10.
 */

public class MainFragment extends AbsFragment implements View.OnClickListener {

    private TabLayout myTab;
    private ViewPager myVp;

    private ImageButton more,search;

    private MainFragmentVPAdapter adapter;

    private List<Fragment> fragments;
    private DynamicFragment dyFragment;
    private FindFragment findFragment;
    private MusicFragment musicFragment;
    private UserFragment userFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FragmentTransaction transaction1;
    private FragmentTransaction transaction2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initDatas();
        myVp.setAdapter(adapter);
        myTab.setupWithViewPager(myVp);
        myVp.setOffscreenPageLimit(4);
        myTab.getTabAt(0).setText("我的");
        myTab.getTabAt(1).setText("音乐");
        myTab.getTabAt(2).setText("动态");
        myTab.getTabAt(3).setText("发现");


    }
    private void initView() {
        myTab=bindView(R.id.tablayout_mainfragment);
        myVp=bindView(R.id.vp_mainfragment);
        fragments=new ArrayList<>();
        adapter=new MainFragmentVPAdapter(getChildFragmentManager());
        more=bindView(R.id.imgbtn_main_more);
        search=bindView(R.id.imgbtn_main_search);


        manager = getFragmentManager();


        more.setOnClickListener(this);
        search.setOnClickListener(this);
    }
    private void initDatas() {
        dyFragment = new DynamicFragment();
        findFragment = new FindFragment();
        musicFragment = new MusicFragment();
        userFragment = new UserFragment();
        fragments.add(userFragment);
        fragments.add(musicFragment);
        fragments.add(dyFragment);
        fragments.add(findFragment);

        adapter.setContext(context);
        adapter.setFragments(fragments);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_main_more:
                transaction1 = manager.beginTransaction();
//                transaction1.add(R.id.framlayout_mainfragment,new MoreFragment());
                transaction1.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);
                transaction1.add(R.id.framlayout_mainfragment,new MoreFragment());
                transaction1.commit();
                break;
            case R.id.imgbtn_main_search:
                transaction2 = manager.beginTransaction();
                transaction2.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);
                transaction2.add(R.id.framlayout_mainfragment,new SearchFragment());
                transaction2.commit();
                break;
        }
    }


}
