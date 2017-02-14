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
import com.example.dllo.newbaidumusic.bean.VideoBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/13.
 */

public class VideoFragmentRVAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private VideoBean.ResultBean data;
    private Context context;

    public void setData(VideoBean.ResultBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createRvViewHolder(context,parent,R.layout.item_video_recyclerview);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_video_title,data.getMv_list().get(position).getTitle());
        holder.setText(R.id.tv_video_create,data.getMv_list().get(position).getArtist());
        holder.setImg(R.id.img_video_main,data.getMv_list().get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getMv_list().size():0;
    }

}
