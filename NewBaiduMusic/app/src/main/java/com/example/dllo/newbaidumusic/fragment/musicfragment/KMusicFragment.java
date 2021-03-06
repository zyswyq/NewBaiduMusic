package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.KMusicLVAdapter;
import com.example.dllo.newbaidumusic.adapter.KMusicRVAdapter;
import com.example.dllo.newbaidumusic.bean.KmusicBannerBean;
import com.example.dllo.newbaidumusic.bean.KmusicBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/9.
 */

public class KMusicFragment extends AbsFragment {

    private RecyclerView recyclerView;
    private ListView listView;
    private KMusicRVAdapter adapter;
    private KMusicLVAdapter lvAdapter;
    private KmusicBean data;
    private Banner banner;
    private List<String> url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_kmusic,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        url=new ArrayList<>();
        recyclerView=bindView(R.id.recycler_kmusic);
        banner=bindView(R.id.kmusic_banner);

        banner.setDelayTime(2000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setBannerStyle(BannerConfig.LEFT);

        listView=bindView(R.id.listView_kmusic);
        adapter=new KMusicRVAdapter();
        lvAdapter=new KMusicLVAdapter();
        lvAdapter.setContext(context);
        adapter.setContext(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NetTool.getInstance().startRequest(URLBean.KMUSIC_BANNER, KmusicBannerBean.class, new CallBack<KmusicBannerBean>() {
            @Override
            public void onSuccess(KmusicBannerBean responce) {
                for (int i = 0; i < responce.getResult().size(); i++) {
                    url.add(responce.getResult().get(i).getPicture());
                }
               banner.setImages(url);
            }

            @Override
            public void onError(Throwable e) {

            }
        });


        NetTool.getInstance().startRequest(URLBean.KMUSIC_LISTDATA, KmusicBean.class, new CallBack<KmusicBean>() {
            @Override
            public void onSuccess(KmusicBean responce) {
                data = responce;
                lvAdapter.setData(data);
                listView.setAdapter(lvAdapter);


            }

            @Override
            public void onError(Throwable e) {

            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(adapter);
    }


}
