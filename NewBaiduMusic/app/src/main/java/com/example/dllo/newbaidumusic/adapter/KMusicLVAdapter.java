package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.KmusicBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by dllo on 17/2/15.
 */

public class KMusicLVAdapter extends BaseAdapter {

    private Context context;
    private KmusicBean data;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(KmusicBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?10:0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.getResult().getItems().get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createListViewHolder(context,view,viewGroup, R.layout.item_kmusiclv);
        baseViewHolder.setText(R.id.tv_kmusiclvtitle,data.getResult().getItems().get(i).getSong_title());
        baseViewHolder.setText(R.id.tv_kmusiclvcount,data.getResult().getItems().get(i).getPlay_num()+"人唱过");
        Log.d(TAG, "getView: "+data.getResult().getItems().get(i).getAlbum_title());
        return baseViewHolder.getMview();
    }
}
