package com.example.dllo.newbaidumusic.tool;

import android.app.Application;
import android.content.Context;

import com.example.dllo.newbaidumusic.mydb.DaoMaster;
import com.example.dllo.newbaidumusic.mydb.DaoSession;

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
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }

    public static Context getContext() {


        return context;
    }

    public static DaoMaster getDaoMaster() {
        //初始化helper对象
        DaoMaster.OpenHelper helper=new DaoMaster.DevOpenHelper(getContext(),"MyPerson.db",null);
        daoMaster=new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession==null){
            if (daoMaster==null){
                daoMaster=getDaoMaster();
            }
        }
        daoSession=daoMaster.newSession();
        return daoSession;
    }

}

