package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.bean.ListDetilBean;

/**
 * Created by dllo on 17/2/20.
 */

public class ListDetailLvAdapter extends BaseAdapter{

    private ListDetilBean data;
    private Context context;


    @Override
    public int getCount() {
        return data!=null?data.getSong_list().size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.getSong_list().get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
