package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.ListDetilBean;
import com.example.dllo.newbaidumusic.minterface.OnNewRecItemClick;
import com.example.dllo.newbaidumusic.minterface.OnRecItemClick;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/20.
 */

public class ListDetailRVAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private OnNewRecItemClick onRecItemClick;
    private ListDetilBean data;
    private Context context;
    private int k=0;

    public void setOnRecItemClick(OnNewRecItemClick onRecItemClick) {
        this.onRecItemClick = onRecItemClick;
    }

    public void setData(ListDetilBean data) {
        this.data = data;
        k=data.getSong_list().size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = BaseViewHolder.createRvViewHolder(context, parent, R.layout.item_listdetillv);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecItemClick.onClick(position);
            }
        });
        switch (position) {

            case 0:
                holder.setImg(R.id.img_listdetil_small, R.mipmap.img_king_mask01);
                break;
            case 1:
                holder.setImg(R.id.img_listdetil_small, R.mipmap.img_king_mask02);
                break;
            case 2:
                holder.setImg(R.id.img_listdetil_small, R.mipmap.img_king_mask03);
                break;
            default:
                holder.setImg(R.id.img_listdetil_small,R.mipmap.img_king_mask1);
        }
        holder.setImg(R.id.img_listdetil_big,data.getSong_list().get(position).getPic_small());
        holder.setText(R.id.tv_listdetil_title,data.getSong_list().get(position).getTitle());
        holder.setText(R.id.tv_listdetil_singer,data.getSong_list().get(position).getAuthor());
        holder.setText(R.id.tv_listdetil_num,position+1+"");
    }


    @Override
    public int getItemCount() {
        return data.getSong_list().size();
    }
}
