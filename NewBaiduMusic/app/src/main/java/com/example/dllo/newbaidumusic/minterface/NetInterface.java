package com.example.dllo.newbaidumusic.minterface;

/**
 * Created by dllo on 17/2/10.
 */

public interface NetInterface {

    public void startRequest(String url, CallBack<String> callBack);
    public <T>void startRequest(String url, Class<T> tclass, CallBack<T> callBack);
}
