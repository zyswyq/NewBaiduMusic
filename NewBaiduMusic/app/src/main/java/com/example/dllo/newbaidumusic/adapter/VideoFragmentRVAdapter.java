package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.VideoBean;
import com.example.dllo.newbaidumusic.minterface.OnRecItemClick;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import java.util.List;

/**
 * Created by dllo on 17/2/13.
 */

public class VideoFragmentRVAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<VideoBean.ResultBean.MvListBean> data;
    private Context context;
    private OnRecItemClick onRecItemClick;

    public void setOnRecItemClick(OnRecItemClick onRecItemClick) {
        this.onRecItemClick = onRecItemClick;
    }

    public void setData(List<VideoBean.ResultBean.MvListBean> data) {
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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecItemClick.onClick(position);
            }
        });
        holder.setText(R.id.tv_video_title,data.get(position).getTitle());
        holder.setText(R.id.tv_video_create,data.get(position).getArtist());
//        Log.d("VideoFragmentRVAdapter", data.get(position).getArtist());

        holder.setImg(R.id.img_video_main,data.get(position).getThumbnail());
//        Log.d("VideoFragmentRVAdapter", data.get(position).getThumbnail2());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

}
