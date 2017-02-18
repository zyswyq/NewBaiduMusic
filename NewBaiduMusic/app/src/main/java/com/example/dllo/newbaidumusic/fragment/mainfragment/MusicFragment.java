package com.example.dllo.newbaidumusic.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.MusicFragmentVPAdapter;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.musicfragment.KMusicFragment;
import com.example.dllo.newbaidumusic.fragment.musicfragment.ListFragment;
import com.example.dllo.newbaidumusic.fragment.musicfragment.RecommendFragment;
import com.example.dllo.newbaidumusic.fragment.musicfragment.SongMenuFragment;
import com.example.dllo.newbaidumusic.fragment.musicfragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 17/2/9.
 * 音乐
 */

public class MusicFragment extends AbsFragment {

    private TabLayout myTab;
    private ViewPager myVp;
    private List<Fragment> fragments;
    private KMusicFragment kFragment;
    private ListFragment listFragment;
    private RecommendFragment recommendFragment;
    private SongMenuFragment songMenuFragment;
    private VideoFragment videoFragment;

    private MusicFragmentVPAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_music,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        adapter.setContext(context);
        adapter.setFragments(fragments);
        myVp.setOffscreenPageLimit(5);
        myVp.setAdapter(adapter);
        myTab.setupWithViewPager(myVp);
        myTab.getTabAt(0).setText("推荐");
        myTab.getTabAt(1).setText("歌单");
        myTab.getTabAt(2).setText("榜单");
        myTab.getTabAt(3).setText("视频");
        myTab.getTabAt(4).setText("K歌");

    }

    private void initView() {
        myTab=bindView(R.id.tablayout_musicfragment);
        myVp=bindView(R.id.vp_musicfragment);
        fragments=new ArrayList<>();
        adapter=new MusicFragmentVPAdapter(getChildFragmentManager());
    }
    private void initData() {
        kFragment = new KMusicFragment();
        listFragment = new ListFragment();
        recommendFragment = new RecommendFragment();
        songMenuFragment = new SongMenuFragment();
        videoFragment = new VideoFragment();

        fragments.add(recommendFragment);
        fragments.add(songMenuFragment);
        fragments.add(listFragment);
        fragments.add(videoFragment);
        fragments.add(kFragment);

    }
}
