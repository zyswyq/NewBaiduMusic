package com.example.dllo.newbaidumusic.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.FindFragmentRVAdapter;
import com.example.dllo.newbaidumusic.bean.FindBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.WebFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;


/**
 * Created by dllo on 17/2/9.
 * 发现
 */

public class FindFragment extends AbsFragment implements View.OnClickListener {

    private ScrollView scrollView;
    private RecyclerView recyclerView;
    private FindBean data;
    private ImageView shop1,shop2,shop3;
    private TextView shotv1,shotv2,shotv3;
    private FindFragmentRVAdapter adapter;
    private TextView shopmore;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_find,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=bindView(R.id.recycler_find);
        scrollView=bindView(R.id.scroll_find);
        shopmore=bindView(R.id.tv_findshopmore);
        shopmore.setOnClickListener(this);
        manager=getActivity().getSupportFragmentManager();

        shop1=bindView(R.id.img_find_shop1);

        //为防止ScrollView在开始就滚动到底,给上方控件设置焦点
        shop1.setFocusable(true);
        shop1.setFocusableInTouchMode(true);
        shop1.requestFocus();
        shop2=bindView(R.id.img_find_shop2);
        shop3=bindView(R.id.img_find_shop3);

        shotv1=bindView(R.id.tv_find_shop1);
        shotv2=bindView(R.id.tv_find_shop2);
        shotv3=bindView(R.id.tv_find_shop3);

        adapter=new FindFragmentRVAdapter();
        adapter.setContext(context);
        adapter.setData(data);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NetTool.getInstance().startRequest(URLBean.FIND_DATA, FindBean.class, new CallBack<FindBean>() {
            @Override
            public void onSuccess(FindBean responce) {
                data=responce;
                adapter.setData(data);
                Glide.with(context).load(data.getResult().getShopInfo().getShopList().get(0).getPicurl()).into(shop1);
                Glide.with(context).load(data.getResult().getShopInfo().getShopList().get(1).getPicurl()).into(shop2);
                Glide.with(context).load(data.getResult().getShopInfo().getShopList().get(2).getPicurl()).into(shop3);
                shotv1.setText(data.getResult().getShopInfo().getShopList().get(0).getTitle());
                shotv2.setText(data.getResult().getShopInfo().getShopList().get(1).getTitle());
                shotv3.setText(data.getResult().getShopInfo().getShopList().get(2).getTitle());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_findshopmore:
                String url="http://music.baidu.com/mall?from=pcweb_music_qrcode";
                Fragment fragment=WebFragment.newInstance(url);
                transaction=manager.beginTransaction();
                transaction.add(R.id.framlayout_mainfragment,fragment);
                transaction.commit();

                break;
        }
    }
}
