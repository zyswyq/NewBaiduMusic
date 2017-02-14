package com.example.dllo.newbaidumusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dllo.newbaidumusic.R;

/**
 * Created by dllo on 17/2/10.
 * 可以滑动退出的Fragment基类
 */

public abstract class AbsSlideFragment extends Fragment implements View.OnTouchListener {

    protected Context context;
    private View view;
    private int distance;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(context).inflate(setLayout(),container,false);
        view.setOnTouchListener(this);
        return view;
    }



    protected abstract int setLayout();

    protected <T extends View> T bindView(int resId) {
        return (T) getView().findViewById(resId);
    }
//    @Override
//    public boolean onTouch(View arg0, MotionEvent event) {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                downX = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                if ((event.getX() - downX) > 100) {
//                    Animation animation = AnimationUtils.loadAnimation(
//                            getActivity(), R.anim.page_slide_right_exit);
//                    parentLayout.startAnimation(animation);
//                    animation.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation arg0) {
//                        } // 在动画开始时使用
//
//                        @Override
//                        public void onAnimationRepeat(Animation arg0) {
//                        } // 在动画重复时使用
//
//                        @Override
//                        public void onAnimationEnd(Animation arg0) {
//                            getFragmentManager().popBackStack();
//                        }
//                    });
//                } else
//                    parentLayout.setPadding(0, 0, 0, 0);
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.i("TaskItemBird", downX + "   " + event.getRawX());
//                int distance = (int) (event.getX() - downX);
//                parentLayout.setPadding(distance, 0, 0, 0);
//                break;
//        }
//        return true;
//    }
    private float downX  ;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                downX = motionEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(motionEvent.getX()-downX)<6) {
                    return false;
                }
                else {
                    if ((motionEvent.getX() - downX) > 300) {
                        Animation animation = AnimationUtils.loadAnimation(
                                getActivity(), R.anim.page_slide_right_exit);
                        view.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation arg0) {
                            } // 在动画开始时使用

                            //
                            @Override
                            public void onAnimationRepeat(Animation arg0) {
                            } // 在动画重复时使用

                            //
                            @Override
                            public void onAnimationEnd(Animation arg0) {
                                getFragmentManager().popBackStack();
                                distance=0;
                                downX=0;
                            }
                        });
                    } else
                        view.setPadding(0, 0, 0, 0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                distance = (int) (motionEvent.getX() - downX);
                if (distance <0) {
                    return false;
                }
                else {
                    view.setPadding(distance, 0, 0, 0);
                }
        }
        return false;
    }
}
