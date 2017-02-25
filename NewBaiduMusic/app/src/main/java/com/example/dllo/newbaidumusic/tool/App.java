package com.example.dllo.newbaidumusic.tool;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 17/2/23.
 * listView不可以使用 ,工具类使用这个
 *
 * 子线程 个数最好别超过手机 核数的的二倍加1
 *
 * 手机最大给个进程为100mb ,不过有4m是必须占用的
 */

public class App extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}

