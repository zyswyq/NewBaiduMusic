package com.example.dllo.newbaidumusic.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.ListDetilBean;

/**
 * Created by dllo on 17/2/20.
 */

public class ListDetailFragment extends AbsFragment{

    private ListView listview;
    private String url;
    private int type;
    private ListDetilBean data;

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
        listview=bindView(R.id.listview_listdetile);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        url=bundle.getString("url");
        type=bundle.getInt("type");

    }


}
