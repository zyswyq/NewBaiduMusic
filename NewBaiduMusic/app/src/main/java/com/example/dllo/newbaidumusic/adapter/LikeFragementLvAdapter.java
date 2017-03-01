package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.mydb.LikeSongBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class LikeFragementLvAdapter extends BaseAdapter{

    private Context context;
    private List<LikeSongBean> data;


    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(List<LikeSongBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createListViewHolder(context,view,viewGroup, R.layout.item_localmusiclv);
        baseViewHolder.setText(R.id.tv_itemloacalmusic_title,data.get(i).getTitle());
        baseViewHolder.setText(R.id.tv_itemloacalmusic_singer,data.get(i).getSinger());


        return baseViewHolder.getMview();
    }
}
