package com.example.dllo.newbaidumusic.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.LocalMusicLVAdapter;
import com.example.dllo.newbaidumusic.bean.LocalSongsBean;
import com.example.dllo.newbaidumusic.minterface.OnLocalPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 17/2/23.
 */

public class LocalMusicFragment extends AbsFragment implements View.OnClickListener{

    private OnLocalPlay onLocalPlay;
    private ListView listView;
    private TextView playall;
    private ImageView back;
    private List<LocalSongsBean> data;
    private int playitem=-1;
    private LocalMusicLVAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onLocalPlay= (OnLocalPlay) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loacalmusic,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=bindView(R.id.listview_localmusic);
        manager=getActivity().getSupportFragmentManager();
        View view1=LayoutInflater.from(context).inflate(R.layout.foot_item,null);
        listView.addFooterView(view1);
        back=bindView(R.id.img_localmusic_fanhui);
        playall=bindView(R.id.tv_localmusic_allplay);
        adapter=new LocalMusicLVAdapter();
        data=new ArrayList<>();
        adapter.setContext(context);
        getLocalMusicData();
        adapter.setData(data);
        adapter.setPlay(playitem);
        listView.setAdapter(adapter);
        playall.setOnClickListener(this);
        back.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setPlay(i);
                adapter.notifyDataSetChanged();
                onLocalPlay.playitem(i,0);
                //TODO
                //向mainActivity传值

            }
        });
    }
    private void getLocalMusicData() {
        Cursor cursor=getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                Long duringTime=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                if (isMusic!=0&&duringTime/60*1000>1){
                    LocalSongsBean bean=new LocalSongsBean(title,singer,url);
                    data.add(bean);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_localmusic_allplay:
                Random random=new Random();
                playitem=random.nextInt(data.size());
                adapter.setPlay(playitem);
                adapter.notifyDataSetChanged();
                //TODO
                //向mainActivity传值
                onLocalPlay.playitem(playitem,2);

                break;
            case R.id.img_localmusic_fanhui:
                transaction=manager.beginTransaction();
                transaction.remove(this);
                transaction.commit();
                break;


        }
    }


}
