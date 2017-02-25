package com.example.dllo.newbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.MoreLvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class MoreFragment extends AbsFragment implements View.OnClickListener {
    private ListView listView;
    private List<String> data;
    private MoreLvAdapter adapter;
    private ImageView back;
    private int[] img={R.mipmap.icon_option_setting_my_message,R.mipmap.icon_option_setting_user_points,R.mipmap.icon_option_setting_vip2,R.mipmap.icon_option_setting_invite_friends,R.mipmap.icon_option_setting_setting,R.mipmap.icon_option_setting_auto_close,R.mipmap.icon_option_setting_pc_sync,R.mipmap.icon_option_setting_app_recommend};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=bindView(R.id.listView_more);
        back=bindView(R.id.img_more_fragment_fanhui);
        back.setOnClickListener(this);
        adapter=new MoreLvAdapter();
        data=new ArrayList<>();
        data.add("我的消息");
        data.add("我的积分");
        data.add("成为白色VIP");
        data.add("邀请有奖");
        data.add("设置");
        data.add("定时关闭");
        data.add("电脑导歌");
        data.add("精品应用");
        adapter.setContext(context);
        adapter.setData(data);
        adapter.setImg(img);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_more_fragment_fanhui:
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.remove(this);
//                transaction.replace(R.id.framlayout_mainfragment,new MainFragment());
                transaction.commit();
                break;
        }
    }
}
