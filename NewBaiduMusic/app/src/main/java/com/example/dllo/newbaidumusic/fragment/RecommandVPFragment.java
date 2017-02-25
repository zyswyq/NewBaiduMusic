package com.example.dllo.newbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;

/**
 * Created by dllo on 17/2/21.
 */

public class RecommandVPFragment extends AbsFragment{

    private ImageView im;

    public static RecommandVPFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        RecommandVPFragment fragment = new RecommandVPFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommand_pic,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        im=bindView(R.id.img_recommand_vppic);
        Bundle bundle=getArguments();
        Glide.with(context).load(bundle.getString("url")).into(im);
        Log.d("Ddddd", "onActivityCreated: "+bundle.getString("url"));
    }
}
