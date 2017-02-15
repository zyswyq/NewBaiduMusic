package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/15.
 */

public class KMusicRVAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private List<String> data;
    private int[] source={R.mipmap.img_k_ktv,R.mipmap.img_k_chinese,R.mipmap.img_k_occident,R.mipmap.img_k_man,R.mipmap.img_k_woman,R.mipmap.img_k_band};


    public void setContext(Context context) {
        this.context = context;
        data=new ArrayList<>();
        data.add("KTV热歌榜");
        data.add("华语金曲");
        data.add("欧美经典");
        data.add("男歌手");
        data.add("女歌手");
        data.add("乐队组合");
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createRvViewHolder(context,parent,R.layout.item_kmusic_rv);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        BaseViewHolder baseViewHolder=holder;
        baseViewHolder.setImg(R.id.cirimg_kmusicrvitem,source[position]);
        baseViewHolder.setText(R.id.tv_kmusicrvitem,data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里跳转
            }
        });
    }

    @Override
    public int getItemCount() {
        return source.length;
    }
}
