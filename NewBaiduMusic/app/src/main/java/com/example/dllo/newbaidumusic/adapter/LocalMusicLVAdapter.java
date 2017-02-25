package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.LocalSongsBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class LocalMusicLVAdapter extends BaseAdapter{

    private List<LocalSongsBean> data;
    private Context context;
    private int play;

    public void setData(List<LocalSongsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPlay(int play) {
        this.play = play;
        notifyDataSetChanged();
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
        MyViewHolder myViewHolder=null;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_localmusiclv,viewGroup,false);
            myViewHolder=new MyViewHolder(view);
            view.setTag(myViewHolder);
        }
        else {
            myViewHolder= (MyViewHolder) view.getTag();
        }

        if (i==play)
        {
            myViewHolder.title.setTextColor(Color.parseColor("#09b9ef"));
            myViewHolder.singer.setTextColor(Color.parseColor("#09b9ef"));
        }
        else {
            myViewHolder.title.setTextColor(Color.parseColor("#454242"));
            myViewHolder.singer.setTextColor(Color.parseColor("#454242"));
        }
        myViewHolder.singer.setText(data.get(i).getSingerName());
        myViewHolder.title.setText(data.get(i).getSongname());
        return view;
    }

    private class MyViewHolder{
        private TextView title,singer;
        public MyViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.tv_itemloacalmusic_title);
            singer = (TextView) view.findViewById(R.id.tv_itemloacalmusic_singer);
        }
    }

}
