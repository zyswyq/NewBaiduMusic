package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class MoreLvAdapter extends BaseAdapter{

    private List<String> data;
    private int[] img;
    private Context context;

    public void setData(List<String> data) {
        this.data = data;
    }

    public void setImg(int[] img) {
        this.img = img;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createListViewHolder(context,view,viewGroup, R.layout.item_more);
        baseViewHolder.setImg(R.id.img_item_morefragment_img,img[i]);
        baseViewHolder.setText(R.id.tv_item_more,data.get(i));

        return baseViewHolder.getMview();
    }
}
