package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.ListFragmentLVAdapter;
import com.example.dllo.newbaidumusic.bean.ListBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.ListDetailFragment;
import com.example.dllo.newbaidumusic.fragment.WebFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;


/**
 * Created by dllo on 17/2/9.
 */

public class ListFragment extends AbsFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ListBean data;
    private ListFragmentLVAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=bindView(R.id.listView_ListFragment);
        listView.setOnItemClickListener(this);
        adapter=new ListFragmentLVAdapter();
        manager = getActivity().getSupportFragmentManager();



        final View foot=LayoutInflater.from(context).inflate(R.layout.foot_item,null);
        NetTool.getInstance().startRequest(URLBean.LIST_DATA, ListBean.class, new CallBack<ListBean>() {
            @Override
            public void onSuccess(ListBean responce) {
                data=responce;
                adapter.setData(data);
                adapter.setContext(context);
                listView.setAdapter(adapter);
                listView.addFooterView(foot);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "网络异常,请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url;
        int type;
        transaction = manager.beginTransaction();
        if (data.getContent().get(i).getWeb_url().equals("")==true){
            url=URLBean.LiST_DETAIL_1+data.getContent().get(i).getType()+URLBean.LiST_DETAIL_2;
            type=1;
            transaction.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);

            transaction.add(R.id.framlayout_mainfragment,ListDetailFragment.newInstance(url,type));
            transaction.commit();
        }else {
            url=data.getContent().get(i).getWeb_url();
            type=2;
            transaction.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);

            transaction.add(R.id.framlayout_mainfragment, WebFragment.newInstance(url));
            transaction.commit();
        }


    }
}
