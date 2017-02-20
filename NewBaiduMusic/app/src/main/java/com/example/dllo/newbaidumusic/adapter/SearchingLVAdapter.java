package com.example.dllo.newbaidumusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.SearchingBean;
import com.example.dllo.newbaidumusic.tool.BaseViewHolder;

/**
 * Created by dllo on 17/2/18.
 */

public class SearchingLVAdapter extends BaseAdapter{

    private SearchingBean data;
    private Context context;
    private boolean issearch=false;
    private boolean is6=false;
    private int j=0;
    private int k=0;
    private int p=0;

    public void setData(SearchingBean data) {
        this.data = data;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SearchingBean getData() {
        return data;
    }

    public void setIssearch(boolean issearch) {
        this.issearch = issearch;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (data==null){
            return 0;
        }
        j = data.getAlbum()!=null?data.getAlbum().size():0;
        k = data.getArtist()!=null?data.getArtist().size():0;
        p = data.getSong()!=null?data.getSong().size():0;
        int i= j + k + p;
        if (i>6){
         is6=true;
        }
        if (issearch==false){
            if (i>=6)
            {
                return 6;

            }
            else {
                return i;
            }
        }
        else {
            return i;
        }

    }



    @Override
    public Object getItem(int i) {
        if (issearch==false){
            switch (i)
            {
                case 1:
                    return data.getArtist().size()>0?data.getArtist().get(i):null;
                case 2:
                    return data.getArtist().size()>1?data.getArtist().get(i):null;
                case 3:
                    return data.getArtist().size()>2?data.getArtist().get(i):null;
                case 4:
                    return data.getSong().size()>0?data.getSong().get(i):null;
                case 5:
                    return data.getSong().size()>1?data.getSong().get(i):null;
                case 6:
                    return data.getAlbum().size()>0?data.getAlbum().get(i):null;
            }
        }else {
            switch (i/10){
                case 0:
                    return data.getAlbum().get(i);
                case 1:
                    return data.getSong().get(i);
                case 2:
                case 3:
                    return data.getArtist().get(i);
            }
        }


        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder=BaseViewHolder.createListViewHolder(context,view,viewGroup,R.layout.item_searchlist);

//        if (issearch)
//        {
            if (i<k){
                baseViewHolder.setText(R.id.tv_item_search,data.getArtist().get(i%10).getArtistname());
                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.ren);
            }else if (i-k<p){

                baseViewHolder.setText(R.id.tv_item_search,data.getSong().get(i-k).getSongname()+data.getSong().get(i-k).getArtistname());
                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.icon_yinyue);
            }else {

                baseViewHolder.setText(R.id.tv_item_search,data.getAlbum().get(i-k-p).getAlbumname()+data.getAlbum().get(i-p-k).getArtistname());
                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.cd);
            }
//        }
//        else
//        {
//            if (i<(k>3?3:k)){
//                baseViewHolder.setText(R.id.tv_item_search,data.getArtist().get(i).getArtistname());
//                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.ren);
//            }   else if ((i-k)<(p>2?2:p))
//            {
//                baseViewHolder.setText(R.id.tv_item_search,data.getSong().get(i-(k>3?3:k)).getSongname()+data.getSong().get(i-(k>3?3:k)).getArtistname());
//                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.icon_yinyue);
//            }else {
//
//                baseViewHolder.setText(R.id.tv_item_search,j!=0?data.getAlbum().get(0).getAlbumname()+data.getAlbum().get(0).getArtistname():"");
//                baseViewHolder.setImg(R.id.img_item_search,R.mipmap.cd);
//            }
//        }

        return baseViewHolder.getMview();
    }
}
