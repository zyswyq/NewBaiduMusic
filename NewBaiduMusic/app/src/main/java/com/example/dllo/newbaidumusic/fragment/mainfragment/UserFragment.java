package com.example.dllo.newbaidumusic.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;

/**
 * Created by dllo on 17/2/9.
 * 我的
 */

public class UserFragment extends AbsFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_user,container,false);
    }
}
