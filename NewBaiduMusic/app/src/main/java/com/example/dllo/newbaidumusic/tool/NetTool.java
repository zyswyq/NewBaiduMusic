package com.example.dllo.newbaidumusic.tool;

import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.minterface.NetInterface;

/**
 * Created by dllo on 17/2/10.
 *
 * 使用这个类的方法
 */
public class NetTool implements NetInterface{
    private static NetTool ourInstance ;
    private NetInterface mInterface;

    public static NetTool getInstance() {
        if (ourInstance==null) {
            synchronized (NetTool.class){
                if (ourInstance==null){
                    ourInstance=new NetTool();
                }
            }
        }
        return ourInstance;
    }

    private NetTool() {
        mInterface=new OKTool();
    }

    @Override
    public void startRequest(String url, CallBack<String> callBack) {
                mInterface.startRequest(url,callBack);
    }

    @Override
    public <T> void startRequest(String url, Class<T> tclass, CallBack<T> callBack) {
        mInterface.startRequest(url,tclass,callBack);
    }
}
