package com.example.dllo.newbaidumusic.mservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.bean.LocalSongsBean;
import com.example.dllo.newbaidumusic.bean.URLBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 17/2/22.
 */

public class SongService extends Service {


    private MediaPlayer mediaPlayer;

    private List<LocalSongsBean> localdata;
    private List<String> UrlData;

    private int index;
    private Context context;
    private SongBinder songBinder;

    private boolean IsLocal;


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        localdata = new ArrayList<>();
        UrlData = new ArrayList<>();
        context = this;
        songBinder = new SongBinder();
        getLocalMusicData();
    }

    private void getLocalMusicData() {
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                Long duringTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                if (isMusic != 0 && duringTime / 60 * 1000 > 1) {
                    LocalSongsBean bean = new LocalSongsBean(title, singer, url, duringTime);
                    localdata.add(bean);
                }
            } while (cursor.moveToNext());
            for (LocalSongsBean localSongsBean : localdata) {

            }
        }
        cursor.close();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return songBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.reset();
        mediaPlayer.release();

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class SongBinder extends Binder {

        public String getTitle(){
            return localdata.get(index).getSongname();
        }

        public String getSinger(){
            return localdata.get(index).getSingerName();
        }



        public void setUrlDate(List<String> data){
            UrlData=data;
        }
        public void setIndex(int position) {
            index = position;
        }

        public int getIndex(){
            return index;
        }

        public void ResetPlay() {
            mediaPlayer.reset();
            play();
        }
        public void ResetOnPlay() {
            mediaPlayer.reset();
            playOnLine();
        }


        public void lastOnsong(){
            index--;
            if (index<0)
            {
                index=UrlData.size()-1;
            }
            mediaPlayer.reset();
            playOnLine();

        }

        public void playOnLine() {
            if (UrlData.size() == 0) {
                Toast.makeText(context, "网络不好", Toast.LENGTH_SHORT).show();
            } else {

                try {
                    if (index < UrlData.size()) {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(UrlData.get(index));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        public void play() {
            if (localdata.size() == 0) {
                Toast.makeText(context, "没有歌曲", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    if (index < localdata.size()) {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(localdata.get(index).getUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Intent sendMySong = new Intent("my_song");
                        sendMySong.putExtra("songBean", (Serializable) localdata.get(index));
                        sendBroadcast(sendMySong);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean isPlaying() {
            return mediaPlayer.isPlaying();
        }

        public void pause() {
            mediaPlayer.pause();
        }

        public void continuePlay() {
            mediaPlayer.start();
        }


        //获取总时长
        public long getDuration() {
            if (localdata.size()>index){
                return localdata.get(index).getDuration();
            }
            return 0;

            //TODO
            //判断后返回
        }

        public long getOnDuration() {
//            return UrlData.get(index).();
            return mediaPlayer.getDuration();

            //TODO
            //判断后返回
        }

        //获取正在播放的进度
        public int getCurrentPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        public void setMediaProgress(int index) {
            mediaPlayer.seekTo(index);

        }

        public void nextOnLineSong(int type) {
            switch (type) {
                case 0:
                    index++;
                    if (index >= UrlData.size()) {
                        index = 0;
                    }
                    mediaPlayer.reset();
                    playOnLine();
                    break;
                case 1:
                    mediaPlayer.reset();
                    playOnLine();
                    break;
                case 2:
                    Random random = new Random();
                    index = random.nextInt(UrlData.size());
                    mediaPlayer.reset();
                    playOnLine();
                    break;
                case 3:
                    index++;
                    if (index>=UrlData.size()){

                    }
                    mediaPlayer.reset();
            }
        }

        public void nextsong(int type) {

            switch (type) {
                case 0:
                    index++;
                    if (index >= localdata.size()) {
                        index = 0;
                    }
                    mediaPlayer.reset();
                    Log.d("SongBinder", "dasda" + index);
                    play();
                    break;
                case 1:
                    mediaPlayer.reset();
                    play();
                    break;
                case 2:
                    Random random = new Random();
                    index = random.nextInt(localdata.size());
                    mediaPlayer.reset();
                    play();
                    break;
            }

            Intent sendMySong = new Intent("my_song");
            sendMySong.putExtra("songBean", (Serializable) localdata.get(index));
            sendBroadcast(sendMySong);
        }


    }


}
