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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.MainTabPopVpAdapter;
import com.example.dllo.newbaidumusic.bean.LocalSongsBean;
import com.example.dllo.newbaidumusic.bean.OnLineSongBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.MainFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.minterface.OnLinePlay;
import com.example.dllo.newbaidumusic.minterface.OnLocalPlay;
import com.example.dllo.newbaidumusic.mservice.SongService;
import com.example.dllo.newbaidumusic.mydb.DBTool;
import com.example.dllo.newbaidumusic.mydb.LikeSongBean;
import com.example.dllo.newbaidumusic.tool.NetTool;
import com.example.dllo.newbaidumusic.view.RunTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements OnLocalPlay, View.OnClickListener, OnLinePlay {

    private TextView song, singer;
    private ImageView start, next, list, mainimg;
    private ProgressBar progressBar;

    private List<OnLineSongBean> onlinedata;//这里存所有数据
    private List<String> onLineUrl;//这里存所有歌曲的url 传给Service
    private List<String> songid = new ArrayList<>();

    private LinearLayout maintab;

    private Boolean IsActivity = true;//用来让while一直循环的
    private SongService.SongBinder songBinder;

    private Intent intent;
    private ServiceConnection connection;

    //    private int Index;//第几首
    private int IsPlay = 0;//0的时候点击无反应,1时为正在播放,2时是暂停状态
    private SongReceiver sonsReceiver;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private boolean ISLOCALMUSIC = true;//为1的时候播放本地音乐

    private boolean IsEnd;//判断for语句是否循环结束

    private int Order = 0;//为0的时候是循环播放,为1的时候单曲循环,为2的时候随机播放,3为顺序播放
    private LocalSongsBean songbean;
    private PopupWindow popupWindow;

    //下边是popupwindow中的组件
    private SeekBar seekBar;
    private ViewPager viewPager;
    private ImageView backImg, backgroundImg, likeImg, playImg, lastImg, nextImg, playTypeImg;
    private ListView listView;
    private TabLayout tabLayout;
    private String urlInside = "";
    private ExecutorService singleThreadExecutor;

    private MainTabPopVpAdapter adapter;
    private LikeSongBean songBean;
    private View view;


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
//        onlinedata = new ArrayList<>();
//        onLineUrl = new ArrayList<>();
        mainimg = (ImageView) findViewById(R.id.img_maintab);
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
    public void playitem(int position, int type, boolean isLocal) {
        songBinder.setIndex(position);
        if (songBinder.isPlaying()) {
            songBinder.ResetPlay();
        } else {
            songBinder.play();
        }
        IsPlay = 1;
        Order = type;
        ISLOCALMUSIC = isLocal;
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
                                if (ISLOCALMUSIC) {
                                    if (songBinder.getDuration() - songBinder.getCurrentPosition() < 1000) {
                                        songBinder.nextsong(Order);

                                    } else {
                                        progressBar.setMax((int) songBinder.getDuration());
                                        progressBar.setProgress(songBinder.getCurrentPosition());
                                    }
                                } else {
                                    if (songBinder.getOnDuration() - songBinder.getCurrentPosition() < 1000) {
                                        songBinder.nextOnLineSong(Order);
                                    } else {
                                        progressBar.setMax((int) songBinder.getOnDuration());
                                        progressBar.setProgress(songBinder.getCurrentPosition());
                                    }
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
        unregisterReceiver(sonsReceiver);
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
                    if (ISLOCALMUSIC) {
                        songBinder.nextsong(Order);
                    } else {
                        songBinder.nextOnLineSong(Order);
                        song.setText(onlinedata.get(songBinder.getIndex()).getSonginfo().getTitle());
                        singer.setText(onlinedata.get(songBinder.getIndex()).getSonginfo().getAuthor());
                        Glide.with(MainActivity.this).load(onlinedata.get(songBinder.getIndex()).getSonginfo().getPic_premium()).into(mainimg);
                    }
                    IsPlay = 1;
                    setBtnImg();
                }
                break;
            case R.id.linear_maintab:
                if (ISLOCALMUSIC == false) {
                    openPop();
                }


                break;
        }


    }

    //在这里弹出popupwindow
    private void openPop() {
        view = LayoutInflater.from(this).inflate(R.layout.pop_maintab, null);
        setView(view);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        View view1 = LayoutInflater.from(this).inflate(R.layout.fragment_main, null);
        popupWindow.showAtLocation(view1, Gravity.BOTTOM, 0, 0);

    }

    //在这里设置view的各种属性
    private void setView(View view) {
        //在这里初始化popupwindow里的组件backImg, backgroundImg, likeImg, playImg, lastImg, nextImg, playTypeImg;
        backgroundImg = (ImageView) view.findViewById(R.id.img_mainpopbackground);
        backImg = (ImageView) view.findViewById(R.id.img_mainpopback);
        likeImg = (ImageView) view.findViewById(R.id.img_mainpoplike);
        playImg = (ImageView) view.findViewById(R.id.img_mainpopplay);
        lastImg = (ImageView) view.findViewById(R.id.img_mainpoplast);
        nextImg = (ImageView) view.findViewById(R.id.img_mainpopnext);
        playTypeImg = (ImageView) view.findViewById(R.id.img_mainpoptype);
        viewPager = (ViewPager) view.findViewById(R.id.vp_mainpop);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_mainpop);


        if (ISLOCALMUSIC) {

        } else if (onlinedata != null) {
            setLikeImg();
            adapter = new MainTabPopVpAdapter();
            adapter.setContext(this);
            adapter.setData(onlinedata.get(songBinder.getIndex()));

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(1);


            setPopBtn();

            backImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    setBtnImg();
                    setMainTabText();
                }
            });
            likeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (DBTool.getInstance().isSava(songBean)) {
                        likeImg.setImageResource(R.mipmap.bt_playpage_button_like_normal_new);
                        DBTool.getInstance().deleteBySongId(songBean.getSongId());
                    } else {
                        likeImg.setImageResource(R.mipmap.bt_playpage_button_like_hl_new);
                        DBTool.getInstance().insertLike(songBean);
                        Log.d("alalala", songBean.getSongId());
                    }
                }
            });
            playImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (songBinder.isPlaying()) {
                        songBinder.pause();
                        setPopPlayImg();
                        setBtnImg();
                    } else {
                        songBinder.continuePlay();
                        setBtnImg();
                        setPopPlayImg();
                    }
                }
            });
            lastImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    songBinder.lastOnsong();
                    adapter.setData(onlinedata.get(songBinder.getIndex()));
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);
                    adapter.notifyDataSetChanged();
                    setPopPlayImg();
                    setBtnImg();
                    setLikeImg();

                }
            });
            nextImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    songBinder.nextOnLineSong(Order);
                    if (songBinder.getIndex() < onlinedata.size()) {
                        adapter.setData(onlinedata.get(songBinder.getIndex()));
                    } else {
                        Toast.makeText(MainActivity.this, "顺序播放已无歌曲", Toast.LENGTH_SHORT).show();
                    }
                    viewPager.setAdapter(adapter);
                    viewPager.setCurrentItem(1);

                    adapter.notifyDataSetChanged();
                    setBtnImg();
                    setPopPlayImg();
                    setLikeImg();
                }
            });
            playTypeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Order < 3) {
                        Order++;
                    } else {
                        Order = 0;
                    }
                    setPlayTypeImg();
                }
            });


        }

    }

    private void setLikeImg() {
        if (Order==3&&songBinder.getIndex()>=onlinedata.size()){
            Toast.makeText(this, "顺序播放已结束", Toast.LENGTH_SHORT).show();
        }else {
            songBean = new LikeSongBean();
            songBean.setSongId(songid.get(songBinder.getIndex()));
            songBean.setPicUrl(onlinedata.get(songBinder.getIndex()).getSonginfo().getPic_big());
            songBean.setTitle(onlinedata.get(songBinder.getIndex()).getSonginfo().getTitle());
            songBean.setSinger(onlinedata.get(songBinder.getIndex()).getSonginfo().getAuthor());
            if (DBTool.getInstance().isSava(songBean)) {
                likeImg.setImageResource(R.mipmap.bt_playpage_button_like_hl_new);
            } else {
                likeImg.setImageResource(R.mipmap.bt_playpage_button_like_normal_new);
            }
        }
      


    }

    private void setPopPlayImg() {
        if (songBinder.isPlaying()) {
            playImg.setImageResource(R.mipmap.bt_playpage_button_pause_normal_new);
        } else {
            playImg.setImageResource(R.mipmap.bt_playpage_button_play_normal_new);
        }
    }

    //在这里进行判断并设置按钮初始状态
    private void setPopBtn() {
        setPopPlayImg();
        setPlayTypeImg();
    }

    private void setPlayTypeImg() {
        switch (Order) {
            case 0:
                playTypeImg.setImageResource(R.mipmap.bt_list_button_roundplay_normal);
                break;
            case 1:
                playTypeImg.setImageResource(R.mipmap.bt_list_roundsingle_normal);
                break;
            case 2:
                playTypeImg.setImageResource(R.mipmap.bt_list_random_normal);
                break;
            case 3:
                playTypeImg.setImageResource(R.mipmap.bt_list_order_normal);
                break;
        }
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

    //在这里接受播放网络的数据
    @Override
    public void playonline(List<String> songId, int Index1, boolean IsLocal, String url) {
        if (urlInside.equals(url)) {
            songBinder.setIndex(Index1);
            songBinder.playOnLine();
            song.setText(onlinedata.get(Index1).getSonginfo().getTitle());
            singer.setText(onlinedata.get(Index1).getSonginfo().getAuthor());
            Glide.with(MainActivity.this).load(onlinedata.get(songBinder.getIndex()).getSonginfo().getPic_premium()).into(mainimg);

        } else {
            urlInside = url;
            songid = songId;
            ISLOCALMUSIC = IsLocal;
            getOnLineUrl(songid, Index1);
        }

        ISLOCALMUSIC = IsLocal;

    }

    //在这里解析数据
    private void getOnLineUrl(final List<String> songId, final int index) {
        onlinedata = new ArrayList<>();
        onLineUrl = new ArrayList<>();
        singleThreadExecutor = Executors.newSingleThreadExecutor();
        synchronized (MainActivity.class) {
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    for (final String string : songId) {
                        NetTool.getInstance().startRequest(URLBean.ONLINESONGDATA1 + string + URLBean.ONLINESONGDATA2, new CallBack<String>() {
                            @Override
                            public void onSuccess(String responce) {
                                Log.d("MainActivity", responce);
                                String newres = responce.substring(1, responce.length() - 2);
                                Log.d("MainActivity1", "songId:" + string);
                                Log.d("MainActivity1", URLBean.ONLINESONGDATA1 + string + URLBean.ONLINESONGDATA2);
                                Gson gson = new Gson();
                                OnLineSongBean bean = gson.fromJson(newres, OnLineSongBean.class);


                                if (bean.getBitrate()!=null){
                                    onLineUrl.add(bean.getBitrate().getFile_link());
                                    onlinedata.add(bean);
                                }else {

                                }

                                if (onlinedata.size() == songId.size()) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            //在这里对其进行排序
                                            for (int j = 0; j < songId.size(); j++) {
                                                for (int i = 0; i < onlinedata.size(); i++) {
                                                    if (onlinedata.get(i).getSonginfo().getSong_id().equals(songId.get(j))) {
                                                        OnLineSongBean a = onlinedata.get(j);
                                                        OnLineSongBean b=onlinedata.get(i);
                                                        String p=onLineUrl.get(j);
                                                        String q=onLineUrl.get(i);
                                                        onlinedata.remove(j);
                                                        onLineUrl.remove(j);
                                                        onLineUrl.add(j,q);
                                                        onLineUrl.remove(i);
                                                        onLineUrl.add(i,p);
                                                        onlinedata.add(j,b);
                                                        onlinedata.remove(i);
                                                        onlinedata.add(i,a);
                                                    }
                                                }

                                            }


                                            song.setText(onlinedata.get(index).getSonginfo().getTitle());
                                            singer.setText(onlinedata.get(index).getSonginfo().getAuthor());
                                            Glide.with(MainActivity.this).load(onlinedata.get(index).getSonginfo().getPic_premium()).into(mainimg);
                                            songBinder.setUrlDate(onLineUrl);
                                            Order = 0;


                                            songBinder.setIndex(index);
                                            if (songBinder.isPlaying()) {
                                                songBinder.ResetOnPlay();
                                                song.setText(onlinedata.get(index).getSonginfo().getTitle());
                                                singer.setText(onlinedata.get(index).getSonginfo().getAuthor());
                                                Glide.with(MainActivity.this).load(onlinedata.get(index).getSonginfo().getPic_premium()).into(mainimg);

                                            } else {
                                                songBinder.playOnLine();
                                                song.setText(onlinedata.get(index).getSonginfo().getTitle());
                                                singer.setText(onlinedata.get(index).getSonginfo().getAuthor());
                                                Glide.with(MainActivity.this).load(onlinedata.get(index).getSonginfo().getPic_premium()).into(mainimg);

                                            }
                                            IsPlay = 1;
                                            IsEnd = false;
                                            setBtnImg();
                                        }
                                    });


                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
                    }
                }
            });
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
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

    public void setMainTabText() {
        if (songBinder.getIndex() < onlinedata.size()) {
            song.setText(onlinedata.get(songBinder.getIndex()).getSonginfo().getTitle());
            singer.setText(onlinedata.get(songBinder.getIndex()).getSonginfo().getAuthor());
            Glide.with(MainActivity.this).load(onlinedata.get(songBinder.getIndex()).getSonginfo().getPic_premium()).into(mainimg);
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ISLOCALMUSIC == false &&popupWindow!=null&& popupWindow.isShowing()) {
                setBtnImg();
                setMainTabText();
                popupWindow.dismiss();
            } else if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                MainActivity.this.finish();
            }

            return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (popupWindow.isShowing()) {
            setMainTabText();
            setBtnImg();
        }
        popupWindow.dismiss();

    }

}
