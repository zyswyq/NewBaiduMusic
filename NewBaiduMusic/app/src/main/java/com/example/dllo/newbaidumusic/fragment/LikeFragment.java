package com.example.dllo.newbaidumusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.LikeFragementLvAdapter;
import com.example.dllo.newbaidumusic.minterface.OnLinePlay;
import com.example.dllo.newbaidumusic.mydb.DBTool;
import com.example.dllo.newbaidumusic.mydb.LikeSongBean;
import com.example.dllo.newbaidumusic.tool.StringTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class LikeFragment extends AbsFragment implements View.OnClickListener {

    private OnLinePlay onLinePlay;

    private List<LikeSongBean> likeSongBeen;
    private ImageView imageView,imageView1,imageView2,back;
    private ListView listView;
    private LikeFragementLvAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onLinePlay= (OnLinePlay) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_like,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        likeSongBeen=new ArrayList<>();
        imageView=bindView(R.id.img_likeshow1);
        imageView1=bindView(R.id.img_likeshow2);
        imageView2=bindView(R.id.img_likeshow3);
        back=bindView(R.id.img_like_back);
        back.setOnClickListener(this);
        manager=getActivity().getSupportFragmentManager();
        listView=bindView(R.id.listview_like);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DBTool.getInstance().deleteBySongId(likeSongBeen.get(i).getSongId());
                likeSongBeen= DBTool.getInstance().queryAll();
                adapter.setData(likeSongBeen);
                listView.setAdapter(adapter);

                return false;
            }
        });

        View view1=LayoutInflater.from(context).inflate(R.layout.foot_item,null);
        listView.addFooterView(view1);
        adapter=new LikeFragementLvAdapter();

        likeSongBeen= DBTool.getInstance().queryAll();
        for (LikeSongBean songBean : likeSongBeen) {
            Log.d("alalala", songBean.getSongId());
        }
        adapter.setContext(context);
        adapter.setData(likeSongBeen);
        listView.setAdapter(adapter);
        if (likeSongBeen!=null&&likeSongBeen.size()!=0){
            Glide.with(context).load(likeSongBeen.get(0).getPicUrl()).into(imageView);
            if (likeSongBeen.size()>1){
                Glide.with(context).load(likeSongBeen.get(1).getPicUrl()).into(imageView1);
            }
            if (likeSongBeen.size()>2){
                Glide.with(context).load(likeSongBeen.get(2).getPicUrl()).into(imageView2);
            }
        }


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> songid=new ArrayList<>();

                for (LikeSongBean songBean : likeSongBeen) {
                    songid.add(songBean.getSongId());

                }

                onLinePlay.playonline(songid,i,false,"local");
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_like_back:
                transaction=manager.beginTransaction();
                transaction.remove(this);
                transaction.commit();

                break;
        }
    }
}
