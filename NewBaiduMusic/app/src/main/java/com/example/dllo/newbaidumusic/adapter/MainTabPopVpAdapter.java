package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.OnLineSongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/27.
 */

public class MainTabPopVpAdapter extends PagerAdapter{
    private List<View> viewList = new ArrayList<View>();
    private Context context;
    private OnLineSongBean data;

    public void setData(OnLineSongBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v=null;
       switch (position){
           case 0:
               v = LayoutInflater.from(context).inflate(R.layout.vp_songsinger, container, false);
               break;
           case 1:
               v = LayoutInflater.from(context).inflate(R.layout.vp_main, container, false);
               ImageView mainimg = (ImageView) v.findViewById(R.id.img_popvpmainimg);
               TextView title = (TextView) v.findViewById(R.id.tv_popvpmaintitle);
               TextView singer = (TextView) v.findViewById(R.id.tv_popvpmainsinger);
               if (!data.getSonginfo().getPic_huge().equals("")){
                   Glide.with(context).load(data.getSonginfo().getPic_huge()).error(R.mipmap.ic_launcher).into(mainimg);
               }
               else if (!data.getSonginfo().getPic_big().equals("")){
                   Glide.with(context).load(data.getSonginfo().getPic_big()).error(R.mipmap.ic_launcher).into(mainimg);
               }
               title.setText(data.getSonginfo().getTitle());
               singer.setText(data.getSonginfo().getAuthor());
               break;
           case 2:
               v = LayoutInflater.from(context).inflate(R.layout.vp_lrc, container, false);
               break;
       }

        viewList.add(v);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
