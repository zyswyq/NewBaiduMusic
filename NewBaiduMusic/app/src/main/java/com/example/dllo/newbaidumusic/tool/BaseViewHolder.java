package com.example.dllo.newbaidumusic.tool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;

/**
 * Created by dllo on 17/2/14.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View mview;

    private SparseArray<View> mSpareArray;

    private Context context;
    public BaseViewHolder(View itemView, Context context) {
        super(itemView);
        mview=itemView;
        mSpareArray=new SparseArray<>();
        this.context=context;
    }

    //针对RV的初始化操作
    public static BaseViewHolder createRvViewHolder(Context context, ViewGroup group, int layoutId){
        View itemView= LayoutInflater.from(context).inflate(layoutId,group,false);
        BaseViewHolder holder=new BaseViewHolder(itemView,context);
        return holder;
    }

    //针对ListView的初始化操作
    public static BaseViewHolder createListViewHolder(Context context,View view, ViewGroup group, int layoutId){
        BaseViewHolder holder=null;
        if (view==null){
            view =LayoutInflater.from(context).inflate(layoutId,group,false);
            holder=new BaseViewHolder(view,context);
            view.setTag(holder);
        }
        else {
            holder= (BaseViewHolder) view.getTag();
        }
        return holder;
    }

    public <T extends View> T getView(int id){
        View view=mSpareArray.get(id);
        if (view==null)
        {
            view=mview.findViewById(id);
            mSpareArray.put(id,view);
        }
        return (T) view;
    }


    public BaseViewHolder setText(int id,String s)
    {
        TextView textView=getView(id);
        if (s!=null){
            textView.setText(s);
        }
        return this;
    }

    public BaseViewHolder setImg(int id,String url){
        ImageView imageView=getView(id);
        if (url!=null){
            Glide.with(context).load(url).into(imageView);
        }
        return this;
    }

    public View getMview() {
        return mview;
    }
}
