package com.example.dllo.newbaidumusic.activity;

import android.app.ActionBar;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.bean.LocalSongsBean;
import com.example.dllo.newbaidumusic.fragment.MainFragment;
import com.example.dllo.newbaidumusic.minterface.OnLocalPlay;
import com.example.dllo.newbaidumusic.mservice.SongService;
import com.example.dllo.newbaidumusic.view.RunTextView;

public class MainActivity extends AppCompatActivity implements OnLocalPlay, View.OnClickListener {

    private TextView song, singer;
    private ImageView start, next, list;
    private ProgressBar progressBar;

    private LinearLayout maintab;

    private Boolean IsActivity = true;//用来让while一直循环的
    private SongService.SongBinder songBinder;

    private Intent intent;
    private ServiceConnection connection;

    private int Index;//第几首
    private int IsPlay = 0;//0的时候点击无反应,1时为正在播放,2时是暂停状态
    private SongReceiver sonsReceiver;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private int ISLOCALMUSIC = 1;//为1的时候播放本地音乐

    private int Order = 0;//为0的时候是顺序播放,为1的时候单曲循环,为2的时候随机播放
    private LocalSongsBean songbean;
    private PopupWindow popupWindow;

    //下边是popupwindow中的组件
    private SeekBar seekBar;
    private ViewPager viewPager;
    private ImageView backImg,backgroundImg,likeImg,playImg,lastImg,nextImg,playTypeImg;
    private ListView listView;
    private TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        IsActivity = true;
        initView();
        initEvent();
        //注册广播
        sonsReceiver = new SongReceiver();
        IntentFilter inentfile = new IntentFilter("my_song");
        //注册广播接收器
        registerReceiver(sonsReceiver, inentfile);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                songBinder = (SongService.SongBinder) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        intent = new Intent(this, SongService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);


    }

    private void initEvent() {
        start.setOnClickListener(this);
        next.setOnClickListener(this);
        list.setOnClickListener(this);
        maintab.setOnClickListener(this);
    }

    private void initView() {
        maintab = (LinearLayout) findViewById(R.id.linear_maintab);

        mainFragment = new MainFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.framlayout_mainfragment, mainFragment, "mainfragment");
        transaction.commit();

        start = (ImageView) findViewById(R.id.img_maintab_play);
        song = (RunTextView) findViewById(R.id.tv_maintab_song);
        singer = (RunTextView) findViewById(R.id.tv_maintab_singer);
        progressBar = (ProgressBar) findViewById(R.id.progress_maintab);
        next = (ImageView) findViewById(R.id.img_maintab_next);
        list = (ImageView) findViewById(R.id.img_maintab_list);
    }

    //在这里接受本地歌曲传来的数据
    @Override
    public void playitem(int position, int type) {
        songBinder.setIndex(position);
        if (songBinder.isPlaying()) {
            songBinder.ResetPlay();
        } else {
            songBinder.play();
        }
        IsPlay = 1;
        Order = type;
        setBtnImg();
    }

    @Override
    protected void onStart() {
        super.onRestart();
        IsActivity = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IsActivity = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("12345678911111", "run: ");

                while (IsActivity) {
                    try {

                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //因为线程刚执行的时候,服务还没有绑定
                    //也就是说songBinder对象是空的,当songBinder对象不为空时
                    //说明服务已经绑定,当isPlaying返回true,说明正在播放歌曲
                    if (songBinder != null && songBinder.isPlaying()) {
                        //获取值的过程可以在子线程中操作
                        //但是更改UI的过程,需要在主线程中执行(seekbar可以在子线程中更改,它的特性是在源码中已经跳到主线程了)

                        //在UI线程中(跳到主线程中)
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (songBinder.getDuration() - songBinder.getCurrentPosition() < 1000) {
                                    songBinder.nextsong(Order);
                                } else {
                                    progressBar.setMax((int) songBinder.getDuration());
                                    progressBar.setProgress(songBinder.getCurrentPosition());
                                }

                            }
                        });
                    }
                }
            }
        }).start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        IsPlay = 0;
        setBtnImg();
        IsActivity = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        IsPlay = 0;
        setBtnImg();
        IsActivity = false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_maintab_play:
                if (songBinder.isPlaying()) {
                    songBinder.pause();
                    IsPlay = 2;
                    setBtnImg();
                } else if (IsPlay == 0) {

                } else {
                    songBinder.continuePlay();
                    IsPlay = 1;
                    setBtnImg();
                }
                break;
            case R.id.img_maintab_next:
                if (IsPlay != 0) {
                    songBinder.nextsong(Order);
                    IsPlay = 1;
                    setBtnImg();
                }
                break;
            case R.id.linear_maintab:
                openPop();

                break;
        }


    }

    //在这里弹出popupwindow
    private void openPop() {
        View view= LayoutInflater.from(this).inflate(R.layout.pop_maintab,null);
        setView(view);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment_main,null);
        popupWindow.showAtLocation(view1, Gravity.BOTTOM,0,0);

    }

    //在这里设置view的各种属性
    private void setView(View view) {


    }

    //设置底部的图标
    private void setBtnImg() {
        switch (IsPlay) {
            case 0:
                break;
            case 1:
                start.setImageResource(R.mipmap.bt_minibar_pause_normal);
                next.setImageResource(R.mipmap.bt_minibar_next_normal);
                list.setImageResource(R.mipmap.bt_minibar_playinglist_normal);
                break;
            case 2:
                start.setImageResource(R.mipmap.bt_minibar_play_normal);
                next.setImageResource(R.mipmap.bt_minibar_next_normal);
                list.setImageResource(R.mipmap.bt_minibar_playinglist_normal);
                break;
        }
    }


    //接收广播,更新数据
    class SongReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            songbean = (LocalSongsBean) intent.getSerializableExtra("songBean");
            song.setText(songbean.getSongname());
            singer.setText(songbean.getSingerName());
        }
    }
}
