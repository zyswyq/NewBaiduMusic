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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 17/2/22.
 */

public class SongService extends Service{


    private MediaPlayer mediaPlayer;
    
    private List<LocalSongsBean> localdata;
    private int index;
    private Context context;
    private SongBinder songBinder;



    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=new MediaPlayer();
        localdata=new ArrayList<>();
        context=this;
        songBinder=new SongBinder();
        getLocalMusicData();
    }

    private void getLocalMusicData() {
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String singer=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                int isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                Long duringTime=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                if (isMusic!=0&&duringTime/60*1000>1){
                    LocalSongsBean bean=new LocalSongsBean(title,singer,url,duringTime);
                    localdata.add(bean);
                }
            }while (cursor.moveToNext());
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

    public class SongBinder extends Binder{
        public void setIndex(int position){
            index =position;
        }

        public void ResetPlay(){
            mediaPlayer.reset();
            play();
        }
        public void play(){
            if (localdata.size()==0){
                Toast.makeText(context, "没有歌曲", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    if (index<localdata.size()){
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(localdata.get(index).getUrl());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Intent sendMySong=new Intent("my_song");
                        sendMySong.putExtra("songBean", (Serializable) localdata.get(index));
                        sendBroadcast(sendMySong);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public boolean isPlaying(){
            return mediaPlayer.isPlaying();
        }

        public void pause(){
            mediaPlayer.pause();
        }

        public void continuePlay(){
            mediaPlayer.start();
        }


        //获取总时长
        public long getDuration(){
            return localdata.get(index).getDuration();
        }

        //获取正在播放的进度
        public int getCurrentPosition(){
            return mediaPlayer.getCurrentPosition();
        }

        public void setMediaProgress(int index){
            mediaPlayer.seekTo(index);

        }

        public void nextsong(int type){

            switch (type) {
                case 0:
                    index++;
                    if (index>=localdata.size())
                    {
                        index=0;
                    }
                    mediaPlayer.reset();
                    Log.d("SongBinder", "dasda"+index);
                    play();
                    break;
                case 1:
                    mediaPlayer.reset();
                    play();
                    break;
                case 2:
                    Random random=new Random();
                    index=random.nextInt(localdata.size());
                    mediaPlayer.reset();
                    play();
                    break;
            }

            Intent sendMySong=new Intent("my_song");
            sendMySong.putExtra("songBean", (Serializable) localdata.get(index));
            sendBroadcast(sendMySong);


        }



    }


}
