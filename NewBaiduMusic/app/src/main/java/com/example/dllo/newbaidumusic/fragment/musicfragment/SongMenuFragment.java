package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.SongMenuFragmentRVAdapter;
import com.example.dllo.newbaidumusic.bean.SongMenuBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by dllo on 17/2/9.
 */

public class SongMenuFragment extends AbsFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private SongMenuFragmentRVAdapter adapter;
    private SongMenuBean data;



    private TextView hottv,newtv;
    private int page=1;
    private int HOTSELECT=1; //1视为最热被显示//0为最新

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_songmenu,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hottv=bindView(R.id.tv_songmenu_hot);
        newtv=bindView(R.id.tv_songmenu_new);

        recyclerView=bindView(R.id.recycler_songmenu);
        adapter=new SongMenuFragmentRVAdapter();
        adapter.setContext(context);
        adapter.setData(data);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hottv.setOnClickListener(this);
        newtv.setOnClickListener(this);
        getHotData();


    }

    private void getHotData(){
        NetTool.getInstance().startRequest(URLBean.SONGMENU_HOTDATA, SongMenuBean.class, new CallBack<SongMenuBean>() {
            @Override
            public void onSuccess(SongMenuBean responce) {
                data=responce;
                adapter.setContext(context);
                adapter.setData(data);
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getNewData(){
        NetTool.getInstance().startRequest(URLBean.SONGMENU_NEWDATA, SongMenuBean.class, new CallBack<SongMenuBean>() {
            @Override
            public void onSuccess(SongMenuBean responce) {
                data=responce;
                adapter.setContext(context);
                adapter.setData(data);
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_songmenu_hot:
                if(HOTSELECT==1){

                }else {
                    hottv.setTextColor(Color.parseColor("#09b9ef"));
                    newtv.setTextColor(Color.parseColor("#aba8a8"));
                    HOTSELECT=1;
                    getHotData();
                }
                break;
            case R.id.tv_songmenu_new:
                if(HOTSELECT==0){

                }else {
                    newtv.setTextColor(Color.parseColor("#09b9ef"));
                    hottv.setTextColor(Color.parseColor("#aba8a8"));
                    HOTSELECT=0;
                    getNewData();
                }
                break;
        }
    }
}
