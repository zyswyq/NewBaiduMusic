package com.example.dllo.newbaidumusic.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.swipebackfragment.SwipeBackFragment;

/**
 * Created by dllo on 17/2/10.
 * 可以滑动退出的Fragment基类
 */

public abstract class AbsSlideFragment extends SwipeBackFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    protected abstract int setLayout();

    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    protected <T extends View> T bindView(int resId) {
        return (T) getView().findViewById(resId);
    }



}
