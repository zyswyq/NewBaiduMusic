package com.example.dllo.newbaidumusic.fragment.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.LocalMusicFragment;

/**
 * Created by dllo on 17/2/9.
 * 我的
 */

public class UserFragment extends AbsFragment implements View.OnClickListener {

    private LinearLayout sdplay;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_user,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sdplay=bindView(R.id.linearlayout_user_sdmusic);
        manager=getActivity().getSupportFragmentManager();
        sdplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout_user_sdmusic:
                transaction=manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);
                transaction.add(R.id.framlayout_mainfragment,new LocalMusicFragment());
                transaction.commit();
                break;
        }
    }
}
