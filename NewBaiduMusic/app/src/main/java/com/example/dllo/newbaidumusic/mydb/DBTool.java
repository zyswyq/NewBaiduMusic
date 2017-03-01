package com.example.dllo.newbaidumusic.mydb;

import android.widget.Toast;

import com.example.dllo.newbaidumusic.bean.OnLineSongBean;
import com.example.dllo.newbaidumusic.tool.App;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class DBTool {
    private LikeSongBeanDao likeSongBeanDao;
    private static DBTool ourInstance;

    public static DBTool getInstance(){
        if (ourInstance==null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        return ourInstance;
    }

    private DBTool(){
        likeSongBeanDao=App.getDaoSession().getLikeSongBeanDao();
    }

    public void insertLike(LikeSongBean likeSongBean){
        if (!isSava(likeSongBean)) {
            likeSongBeanDao.insert(likeSongBean);
        }
    }

    public List<LikeSongBean> queryAll(){
        List<LikeSongBean> list=likeSongBeanDao.loadAll();
        return list;
    }

    public void deleteBySongId(String songId){
        DeleteQuery<LikeSongBean> deleteQuery=likeSongBeanDao.queryBuilder().where(LikeSongBeanDao.Properties.SongId.eq(songId)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public boolean isSava(LikeSongBean likeSongBean){
        QueryBuilder<LikeSongBean> builder=likeSongBeanDao.queryBuilder().where(LikeSongBeanDao.Properties.SongId.eq(likeSongBean.getSongId()));
        if (queryAll().size()==0){
            return false;
        }
        Long count =builder.buildCount().count();
        return count>0?true:false;
    }

    public void deleteAll(){

    }


}
