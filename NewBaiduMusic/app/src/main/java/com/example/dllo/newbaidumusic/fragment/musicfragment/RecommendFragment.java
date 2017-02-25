package com.example.dllo.newbaidumusic.fragment.musicfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.RecommandVPAdapter;
import com.example.dllo.newbaidumusic.adapter.RecommendLvAdapter;
import com.example.dllo.newbaidumusic.adapter.RecommendRv1Adapter;
import com.example.dllo.newbaidumusic.bean.CommendBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.fragment.AbsFragment;
import com.example.dllo.newbaidumusic.fragment.RecommandVPFragment;
import com.example.dllo.newbaidumusic.fragment.WebFragment;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/9.
 */

public class RecommendFragment extends AbsFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rv1, rv2, rv3, rv4, rv5, rv6;
    private RecommendRv1Adapter adapter,adapter1,adapter2,adapter3,adapter4,adapter5;
    private RecommendLvAdapter lvadapter;
    private SwipeRefreshLayout refresh;
    
    private ViewPager myVp;
    private List<String> url;
    private Handler handler;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    private List<Bitmap> img1;

    private ImageView img;

    private CommendBean data;

    private ListView listView;
    private List<Fragment> fragments;
    private RecommandVPAdapter myada;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        setMyAdapter();




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       startCirclePlay();


    }
    private void startCirclePlay(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    SystemClock.sleep(3000);
                    changeCirclePlayImg();
                }
            }
        }).start();
    }
    private void changeCirclePlayImg() {


    handler.post(new Runnable() {
        @Override
        public void run() {
            myVp.setCurrentItem(myVp.getCurrentItem() + 1);
        }
    });
}
    //在这里控制轮播图
    private void setMyAdapter() {
    }


    private void initView() {
        manager=getActivity().getSupportFragmentManager();
        handler=new Handler();
        fragments=new ArrayList<>();
        myVp=bindView(R.id.viewpager_recommand);
        url=new ArrayList<>();
        
        img1=new ArrayList<>();
        refresh = bindView(R.id.swipe_recommend);
        refresh.setOnRefreshListener(this);

        refresh.setColorSchemeResources(R.color.mainFragmentTablayout);
        rv1 = bindView(R.id.recycler_recommand1);
        rv2 = bindView(R.id.recycler_recommand2);
        rv3 = bindView(R.id.recycler_recommand3);
        rv4 = bindView(R.id.recycler_recommand4);
        rv5 = bindView(R.id.recycler_recommand5);
        rv6 = bindView(R.id.recycler_recommand6);
        listView=bindView(R.id.listview_recommand);
        adapter = new RecommendRv1Adapter();
        adapter1 = new RecommendRv1Adapter();
        adapter2 = new RecommendRv1Adapter();
        adapter3 = new RecommendRv1Adapter();
        adapter4 = new RecommendRv1Adapter();
        adapter5 = new RecommendRv1Adapter();
        lvadapter=new RecommendLvAdapter();
        myada = new RecommandVPAdapter();
        myada.setContext(context);
        myVp.setCurrentItem(Integer.MAX_VALUE/2);


        lvadapter.setContext(context);
        adapter.setContext(context);
        adapter1.setContext(context);
        adapter2.setContext(context);
        adapter3.setContext(context);
        adapter4.setContext(context);
        adapter5.setContext(context);
        rv1.setLayoutManager(new GridLayoutManager(context,3));
        rv2.setLayoutManager(new GridLayoutManager(context,3));
        rv3.setLayoutManager(new GridLayoutManager(context,3));
        rv4.setLayoutManager(new GridLayoutManager(context,3));
        rv5.setLayoutManager(new GridLayoutManager(context,3));
        rv6.setLayoutManager(new GridLayoutManager(context,3));


    }


    private void initData() {


        NetTool.getInstance().startRequest(URLBean.RECOMMEND_DATA, CommendBean.class, new CallBack<CommendBean>() {
            @Override
            public void onSuccess(CommendBean responce) {
                data = responce;
                for (int i = 0; i < data.getResult().getFocus().getResult().size(); i++) {
                    url.add(data.getResult().getFocus().getResult().get(i).getRandpic());
                }

                myada.setUrl(url);
                myVp.setAdapter(myada);


                
                adapter.setData(data);
                adapter1.setData(data);
                adapter2.setData(data);
                adapter3.setData(data);
                adapter4.setData(data);
                adapter5.setData(data);
                lvadapter.setData(data);

                listView.setAdapter(lvadapter);

                adapter.setType(1);
                rv1.setAdapter(adapter);

                adapter1.setType(2);
                rv2.setAdapter(adapter1);

                adapter2.setType(3);
                rv3.setAdapter(adapter2);

                adapter3.setType(4);
                rv4.setAdapter(adapter3);

                adapter4.setType(5);
                rv5.setAdapter(adapter4);

                adapter5.setType(6);
                rv6.setAdapter(adapter5);

                refresh.setRefreshing(false);

                //在这里调用获得Bitmap格式图片的方法
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        getBitmap(data);
//                    }
//                }).start();

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                transaction=manager.beginTransaction();
                transaction.add(R.id.framlayout_mainfragment, WebFragment.newInstance(data.getResult().getMod_7().getResult().get(i).getType_id()));
                transaction.commit();
            }
        });

    }


    //将图片转为bitmap格式,并且重新设置颜色
    private void getBitmap(CommendBean data) {
        URL url = null;
        for (int i = 0; i < 4; i++) {
            try {
                url = new URL(data.getResult().getScene().getResult().getAction().get(i).getIcon_android().toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                int max = conn.getContentLength();
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                byte[] result = baos.toByteArray();
                Bitmap bitmap=BitmapFactory.decodeByteArray(result, 0, result.length);

                Bitmap updateBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                        bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(updateBitmap);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿的画笔
                final ColorMatrix cm = new ColorMatrix();
                paint.setColorFilter(new ColorMatrixColorFilter(cm));
                paint.setColor(Color.BLACK);
                paint.setAntiAlias(true);
                final Matrix matrix = new Matrix();
                canvas.drawBitmap(bitmap, matrix, paint);
                cm.set(new float[] { 160 / 128f, 0, 0, 0, 0,// 红色值
                                0, 32 / 128f, 0, 0, 0,// 绿色值
                                0, 0, 240 / 128f, 0, 0,// 蓝色值
                                0, 0, 0, 1, 0 // 透明度
                 });
//                paint.setColorFilter();
                paint.setColorFilter(new ColorMatrixColorFilter(cm));
                canvas.drawBitmap(bitmap, matrix, paint);
                img1.add(updateBitmap);

            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img.setImageBitmap(img1.get(0));
                }
            });
        }
    }

    public void getColor(){

    }

    @Override
    public void onRefresh() {
        initData();
    }
}
