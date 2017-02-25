package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.SongMenuDetilBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/22.
 */

public class SongMenuDetilRvAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private SongMenuDetilBean data;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(SongMenuDetilBean data) {
        this.data = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createRvViewHolder(context,parent, R.layout.item_songmenudetil);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_item_songmenudetil_title,data.getContent().get(position).getTitle());
        holder.setText(R.id.tv_item_songmenudetil_singer,data.getContent().get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getContent().size():0;
    }
}
