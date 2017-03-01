package com.example.dllo.newbaidumusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.SongMenuDetilRvAdapter;
import com.example.dllo.newbaidumusic.bean.ListDetilBean;
import com.example.dllo.newbaidumusic.bean.SongMenuDetilBean;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.minterface.OnLinePlay;
import com.example.dllo.newbaidumusic.minterface.OnRecItemClick;
import com.example.dllo.newbaidumusic.tool.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class SongMenuDetilFragment extends AbsFragment implements View.OnClickListener,OnRecItemClick {
    private String url;
    private SongMenuDetilBean data;
    private RecyclerView rec;
    private ImageView img,back;
    private SongMenuDetilRvAdapter adapte;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private OnLinePlay onLinePlay;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onLinePlay= (OnLinePlay) context;
    }

    public static SongMenuDetilFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        SongMenuDetilFragment fragment = new SongMenuDetilFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_songmenu_detile,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img=bindView(R.id.img_songmenudetil_background);
        back=bindView(R.id.img_songmenudetilback);
        rec=bindView(R.id.recycler_songmenudetil);
        manager=getActivity().getSupportFragmentManager();
        adapte=new SongMenuDetilRvAdapter();
        adapte.setContext(context);

        back.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        url=bundle.getString("url");

        NetTool.getInstance().startRequest(url, SongMenuDetilBean.class, new CallBack<SongMenuDetilBean>() {
            @Override
            public void onSuccess(SongMenuDetilBean responce) {
                data=responce;
                adapte.setData(data);
                adapte.setItemClick(SongMenuDetilFragment.this);
                rec.setLayoutManager(new LinearLayoutManager(context));
                rec.setAdapter(adapte);
                Glide.with(context).load(data.getPic_500()).into(img);


            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_songmenudetilback:
                transaction=manager.beginTransaction();
                transaction.remove(this);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onClick(int position) {
        List<String> songid=new ArrayList<>();
        for (SongMenuDetilBean.ContentBean contentBean : data.getContent()) {
            songid.add(contentBean.getSong_id());
        }
        onLinePlay.playonline(songid,position,false,url);
    }
}
