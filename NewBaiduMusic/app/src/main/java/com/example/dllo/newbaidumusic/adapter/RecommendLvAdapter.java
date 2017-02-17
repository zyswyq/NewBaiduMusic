package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.CommendBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/16.
 */

public class RecommendLvAdapter extends BaseAdapter{

    private Context context;
    private CommendBean data;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(CommendBean data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data!=null?data.getResult().getMod_7().getResult().size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.getResult().getMod_7().getResult().get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createListViewHolder(context,view,viewGroup, R.layout.item_recommend_lv);
        baseViewHolder.setImg(R.id.img_command_lvimg,data.getResult().getMod_7().getResult().get(i).getPic());
        baseViewHolder.setText(R.id.tv_command_lvtitle,data.getResult().getMod_7().getResult().get(i).getTitle());
        baseViewHolder.setText(R.id.tv_command_lvcreate,data.getResult().getMod_7().getResult().get(i).getDesc());

        return baseViewHolder.getMview();
    }
}
