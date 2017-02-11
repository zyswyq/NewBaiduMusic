package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.SongMenuFragmentRVAdapter;
import com.example.dllo.newbaidumusic.bean.SongMenuBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;

/**
 * Created by dllo on 17/2/9.
 */

public class SongMenuFragment extends AbsFragment {

    private RecyclerView recyclerView;
    private SongMenuFragmentRVAdapter adapter;
    private SongMenuBean data;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_songmenu,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerView=bindView(R.id.recycler_songmenu);
        adapter=new SongMenuFragmentRVAdapter();

        NetTool.getInstance().startRequest(URLBean.SONGMENU_HOTDATA, SongMenuBean.class, new CallBack<SongMenuBean>() {
            @Override
            public void onSuccess(SongMenuBean responce) {
                data=responce;
                adapter.setContext(context);
                adapter.setData(data);
                recyclerView.setLayoutManager(new GridLayoutManager(context,2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
