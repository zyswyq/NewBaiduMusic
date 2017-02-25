package com.example.dllo.newbaidumusic.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.ListDetailRVAdapter;
import com.example.dllo.newbaidumusic.bean.ListDetilBean;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;

/**
 * Created by dllo on 17/2/20.
 */

public class ListDetailFragment extends AbsFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private String url;
    private int type;
    private ListDetilBean data;
    private ListDetailRVAdapter adapter;
    private ImageView back,background;
    private CollapsingToolbarLayout coll;
    private TextView textView,title;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public static ListDetailFragment newInstance(String url,int type) {
        Bundle args = new Bundle();
        args.putString("url",url);
        args.putInt("type",type);
        ListDetailFragment fragment = new ListDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listdetile,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title=bindView(R.id.tv_listdetiltitle);
        manager=getActivity().getSupportFragmentManager();
        textView=bindView(R.id.tv_listdetil_songnum);
        background=bindView(R.id.img_listdetil_background);
        coll=bindView(R.id.collaps_listdetil);

        back=bindView(R.id.img_listdetilback);
        back.setOnClickListener(this);
        recyclerView=bindView(R.id.recycler_listdetil);
        adapter=new ListDetailRVAdapter();
        adapter.setContext(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        url=bundle.getString("url");
        type=bundle.getInt("type");

        NetTool.getInstance().startRequest(url, ListDetilBean.class, new CallBack<ListDetilBean>() {
            @Override
            public void onSuccess(ListDetilBean responce) {
                data=responce;
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                Glide.with(context).load(data.getBillboard().getPic_s640()).into(background);
//                coll.setContentScrim();
//                coll.setTitle(data.getBillboard().getName());
                title.setText(111111+"");
                textView.setText("播放全部/"+data.getSong_list().size()+"首歌");
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_listdetilback:
                transaction=manager.beginTransaction();
                transaction.remove(this);
                transaction.commit();
                break;

        }
    }
}
