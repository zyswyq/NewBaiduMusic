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

/**
 * Created by dllo on 17/2/13.
 */

public class VideoFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=null;
        View view= LayoutInflater.from(context).inflate(R.layout.item_video_recyclerview,parent,false);
        myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;

        myViewHolder.title.setText(data.getMv_list().get(position).getTitle());
        myViewHolder.create.setText(data.getMv_list().get(position).getArtist());
        Glide.with(context).load(data.getMv_list().get(position).getThumbnail()).into(myViewHolder.mainimg);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getMv_list().size():0;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainimg;
        private TextView title,create;
        public MyViewHolder(View itemView) {
            super(itemView);
            mainimg = (ImageView) itemView.findViewById(R.id.img_video_main);
            title = (TextView) itemView.findViewById(R.id.tv_video_title);
            create = (TextView) itemView.findViewById(R.id.tv_video_create);
        }
    }

}
