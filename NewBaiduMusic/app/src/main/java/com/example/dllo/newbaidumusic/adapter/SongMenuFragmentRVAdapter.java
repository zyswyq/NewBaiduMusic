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

/**
 * Created by dllo on 17/2/11.
 */

public class SongMenuFragmentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private SongMenuBean data;

    public SongMenuFragmentRVAdapter() {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_songmenu_recycler,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.title.setText(data.getDiyInfo().get(position).getTitle());
        myViewHolder.creater.setText("by "+data.getDiyInfo().get(position).getUsername());
        myViewHolder.listener.setText(data.getDiyInfo().get(position).getListen_num()+"");
        Glide.with(context).load(data.getDiyInfo().get(position).getList_pic()).into(myViewHolder.mainimg);

    }

    @Override
    public int getItemCount() {
        return data!=null?data.getDiyInfo().size():0;
    }

    class MyViewHolder  extends RecyclerView.ViewHolder {
        private ImageView mainimg;
        private TextView title,listener,creater;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_songmenu_title);
            listener = (TextView) itemView.findViewById(R.id.tv_songmenu_listen);
            creater = (TextView) itemView.findViewById(R.id.tv_songmenu_create);
            mainimg = (ImageView) itemView.findViewById(R.id.img_songmenu_main);
        }
    }
}
