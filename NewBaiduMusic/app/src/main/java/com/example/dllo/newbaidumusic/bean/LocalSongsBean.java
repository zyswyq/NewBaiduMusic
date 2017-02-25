package com.example.dllo.newbaidumusic.bean;

import java.io.Serializable;

/**
 * Created by dllo on 17/2/23.
 */

public class LocalSongsBean implements Serializable{
    private String songname;
    private String singerName;
    private String url;
    private Long duration;


    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalSongsBean(String songname, String singerName, String url, Long duration) {

        this.songname = songname;
        this.singerName = singerName;
        this.url = url;
        this.duration = duration;
    }

    public LocalSongsBean(String songname, String singerName, String url) {
        this.songname = songname;
        this.singerName = singerName;
        this.url = url;

    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
