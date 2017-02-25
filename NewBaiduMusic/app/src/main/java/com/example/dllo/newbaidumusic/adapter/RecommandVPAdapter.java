package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页轮播图
 * Created by mdw on 2016/4/20.
 */
public class RecommandVPAdapter extends PagerAdapter{
    private static final String TAG = "MusicRecommendViewPager";
    private List<String> url;
    private Context context;
    private List<View> viewList = new ArrayList<View>();

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<View> getViewList() {
        return viewList;
    }

    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position%(url.size());
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_recommand_pic, container, false);
        ImageView img = (ImageView) v.findViewById(R.id.img_recommand_vppic);
        Glide.with(context).load(url.get(position)).into(img);
        viewList.add(v);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position%(url.size());
        container.removeView(viewList.get(position));
    }

}
