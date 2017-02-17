package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.CommendBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by dllo on 17/2/16.
 */

public class RecommendRv1Adapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Context context;
    private CommendBean data;
    private int type;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(CommendBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.createRvViewHolder(context,parent, R.layout.item_recommend);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        BaseViewHolder baseViewHolder=holder;
        switch (type){

            case 1:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getDiy().getResult().get(position).getTitle());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getDiy().getResult().get(position).getPic());

                break;
            case 2:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getMix_1().getResult().get(position).getTitle());
                baseViewHolder.setText(R.id.tv_command_rvcreater,data.getResult().getMix_1().getResult().get(position).getAuthor());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getMix_1().getResult().get(position).getPic());
                break;
            case 3:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getMix_22().getResult().get(position).getTitle());
                baseViewHolder.setText(R.id.tv_command_rvcreater,data.getResult().getMix_22().getResult().get(position).getAuthor());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getMix_22().getResult().get(position).getPic());
                break;
            case 4:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getMix_9().getResult().get(position).getTitle());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getMix_9().getResult().get(position).getPic());
                break;
            case 5:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getMix_5().getResult().get(position).getTitle());
                baseViewHolder.setText(R.id.tv_command_rvcreater,data.getResult().getMix_5().getResult().get(position).getAuthor());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getMix_5().getResult().get(position).getPic());
                break;
            case 6:
                baseViewHolder.setText(R.id.tv_command_rvtitle,data.getResult().getRadio().getResult().get(position).getTitle());
                baseViewHolder.setImg(R.id.img_command_rvimg,data.getResult().getRadio().getResult().get(position).getPic());
                break;
        }


    }


    @Override
    public int getItemCount() {
        int i=3;
        if (type==1||type==2||type==5||type==6)
        {
            i=6;
        }
        return i;
    }
}
