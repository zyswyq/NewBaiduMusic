package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.SongMenuBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/11.
 */

public class SongMenuFragmentRVAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private SongMenuBean data;

    public SongMenuFragmentRVAdapter() {
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createRvViewHolder(context,parent,R.layout.item_songmenu_recycler);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SongMenuBean getData() {
        return data;
    }

    public void setData(SongMenuBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_songmenu_title,data.getDiyInfo().get(position).getTitle());
        holder.setText(R.id.tv_songmenu_create,data.getDiyInfo().get(position).getUsername());
        holder.setText(R.id.tv_songmenu_listen,data.getDiyInfo().get(position).getListen_num()+"");
        holder.setImg(R.id.img_songmenu_main,data.getDiyInfo().get(position).getList_pic());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getDiyInfo().size():0;
    }
}
