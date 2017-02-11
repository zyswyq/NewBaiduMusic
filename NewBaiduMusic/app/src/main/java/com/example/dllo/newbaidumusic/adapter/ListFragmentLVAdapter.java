package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.ListBean;

/**
 * Created by dllo on 17/2/10.
 */

public class ListFragmentLVAdapter extends BaseAdapter{

    private ListBean data;
    private Context context;

    public ListFragmentLVAdapter() {
    }

    public ListBean getData() {
        return data;
    }

    public void setData(ListBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data!=null?data.getContent().size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.getContent():null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder=null;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_listlv,viewGroup,false);
            myViewHolder=new MyViewHolder(view);
            view.setTag(myViewHolder);
        }else {
            myViewHolder= (MyViewHolder) view.getTag();
        }
        Glide.with(context).load(data.getContent().get(i).getPic_s210()).into(myViewHolder.list);
        myViewHolder.title.setTextColor(Color.parseColor("#000000"));
        myViewHolder.title.setText(data.getContent().get(i).getName());
        myViewHolder.first.setText(" "+data.getContent().get(i).getContent().get(0).getTitle()+"-"+data.getContent().get(i).getContent().get(0).getAuthor());
        myViewHolder.second.setText(" "+data.getContent().get(i).getContent().get(1).getTitle()+"-"+data.getContent().get(i).getContent().get(1).getAuthor());
        myViewHolder.third.setText(" "+data.getContent().get(i).getContent().get(2).getTitle()+"-"+data.getContent().get(i).getContent().get(2).getAuthor());
        if (i==3)
        {
            Glide.with(context).load(data.getContent().get(i).getPic_s192()).into(myViewHolder.list);
            myViewHolder.title.setTextColor(Color.parseColor("#e6bf5d"));
        }
        return view;
    }

    private class MyViewHolder{
        private ImageView list;
        private TextView title,first,second,third;
        public MyViewHolder(View view) {
            list=(ImageView) view.findViewById(R.id.img_listItemList);
            title = (TextView) view.findViewById(R.id.tv_listTitle);
            first = (TextView) view.findViewById(R.id.tv_listFirst);
            second = (TextView) view.findViewById(R.id.tv_listSecond);
            third = (TextView) view.findViewById(R.id.tv_listThird);
        }
    }
}
