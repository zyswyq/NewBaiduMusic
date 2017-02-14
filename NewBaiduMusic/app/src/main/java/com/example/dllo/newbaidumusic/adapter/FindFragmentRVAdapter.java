package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.FindBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/14.
 */

public class FindFragmentRVAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private FindBean data;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(FindBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createRvViewHolder(context,parent, R.layout.item_findrec);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_findhome,data.getResult().getLiveInfo().getLiveList().get(position).getNickname());
        holder.setImg(R.id.img_findmain,data.getResult().getLiveInfo().getLiveList().get(position).getLiveimg());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.getResult().getLiveInfo().getLiveList().size():0;
    }
}
