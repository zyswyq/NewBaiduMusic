package com.example.dllo.newbaidumusic.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.fragment.MainFragment;

import me.yokeyword.swipebackfragment.SwipeBackActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainFragment = new MainFragment();
        manager=getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.framlayout_mainfragment,mainFragment,"mainfragment");
        transaction.commit();
    }
}
