package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.VideoFragmentRVAdapter;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.bean.VideoBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;

import java.io.UnsupportedEncodingException;


/**
 * Created by dllo on 17/2/9.
 */

public class VideoFragment extends AbsFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView menu,newTv,hotTv;
    private String selectMenu="全部";
    private VideoFragmentRVAdapter adapter;
    private VideoBean data;
    private int page=1;
    private int HOTSELECT=1; //1视为最热被显示//0为最新

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_video,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=bindView(R.id.recycler_videofragment);
        menu=bindView(R.id.tv_songmenu_menu);
        newTv=bindView(R.id.tv_songmenu_new);
        hotTv=bindView(R.id.tv_songmenu_hot);
        adapter=new VideoFragmentRVAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter.setContext(context);
        getHotDate();
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
        menu.setOnClickListener(this);
        newTv.setOnClickListener(this);
        hotTv.setOnClickListener(this);
    }

    //获取最热数据
    private void getHotDate() {
        try {
            NetTool.getInstance().startRequest(URLBean.VIDEO_HOT1 + page + URLBean.VIDEO_NEW2+new String(selectMenu.getBytes(),"utf-8"), VideoBean.class, new CallBack<VideoBean>() {
                @Override
                public void onSuccess(VideoBean responce) {
                    data=responce;
                    adapter.setData(data.getResult());
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(context, "网络不好", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //获取最新数据
    private void getNewDate() {
        try {
            NetTool.getInstance().startRequest(URLBean.VIDEO_NEW1 + page + URLBean.VIDEO_NEW2+new String(selectMenu.getBytes(),"utf-8"), VideoBean.class, new CallBack<VideoBean>() {
                @Override
                public void onSuccess(VideoBean responce) {
                    data=responce;
                    adapter.setData(data.getResult());
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(context, "网络不好", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_songmenu_menu:

                break;
            case R.id.tv_songmenu_hot:
                if(HOTSELECT==1){

                }else {
                    hotTv.setTextColor(Color.parseColor("#09b9ef"));
                    newTv.setTextColor(Color.parseColor("#aba8a8"));
                    HOTSELECT=1;
                    getHotDate();
                }
                break;
            case R.id.tv_songmenu_new:
                if(HOTSELECT==0){

                }else {
                    newTv.setTextColor(Color.parseColor("#09b9ef"));
                    hotTv.setTextColor(Color.parseColor("#aba8a8"));
                    HOTSELECT=0;
                    getNewDate();
                }
                break;
        }

    }
}
