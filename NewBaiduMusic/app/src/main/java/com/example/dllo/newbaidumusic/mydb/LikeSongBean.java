package com.example.dllo.newbaidumusic.mydb;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/2/28.
 */

@Entity
public class LikeSongBean {

    @Id
    private String songId;
    private String title;
    private String singer;
    private String picUrl;
    @Generated(hash = 189878527)
    public LikeSongBean(String songId, String title, String singer, String picUrl) {
        this.songId = songId;
        this.title = title;
        this.singer = singer;
        this.picUrl = picUrl;
    }
    @Generated(hash = 135236409)
    public LikeSongBean() {
    }
    public String getSongId() {
        return this.songId;
    }
    public void setSongId(String songId) {
        this.songId = songId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSinger() {
        return this.singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
