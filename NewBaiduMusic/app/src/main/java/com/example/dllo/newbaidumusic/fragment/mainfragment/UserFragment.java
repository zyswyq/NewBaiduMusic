package com.example.dllo.newbaidumusic.fragment.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.LikeFragment;
import com.example.dllo.newbaidumusic.fragment.LocalMusicFragment;
//import com.example.dllo.newbaidumusic.login.LoginPage;

/**
 * Created by dllo on 17/2/9.
 * 我的
 */

public class UserFragment extends AbsFragment implements View.OnClickListener {

    private LinearLayout sdplay;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RelativeLayout likeRelative;
    private ImageView signin;

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
        likeRelative=bindView(R.id.relativelayout_user_favourt);
        likeRelative.setOnClickListener(this);
        signin=bindView(R.id.img_user_sign);
        signin.setOnClickListener(this);
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
            case R.id.relativelayout_user_favourt:
                transaction=manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.page_slide_out,R.anim.no_move);
                transaction.add(R.id.framlayout_mainfragment,new LikeFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.img_user_sign:
//                Intent intent=new Intent(context, LoginPage.class);
//                startActivity(intent);
                break;
        }
    }
}
