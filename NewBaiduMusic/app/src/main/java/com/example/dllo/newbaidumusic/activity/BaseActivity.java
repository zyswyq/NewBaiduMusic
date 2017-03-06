package com.example.dllo.newbaidumusic.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dllo on 17/3/4.
 */

public class BaseActivity extends AppCompatActivity {
    /** Notificationπ‹¿Ì */
    public NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initService();
    }

    /**
     * ≥ı ºªØ“™”√µΩµƒœµÕ≥∑˛ŒÒ
     */
    private void initService() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * «Â≥˝µ±«∞¥¥Ω®µƒÕ®÷™¿∏
     */
    public void clearNotify(int notifyId){
        mNotificationManager.cancel(notifyId);//…æ≥˝“ª∏ˆÃÿ∂®µƒÕ®÷™ID∂‘”¶µƒÕ®÷™
//		mNotification.cancel(getResources().getString(R.string.app_name));
    }

    /**
     * «Â≥˝À˘”–Õ®÷™¿∏
     * */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// …æ≥˝ƒ„∑¢µƒÀ˘”–Õ®÷™
    }

    /**
     * @ªÒ»°ƒ¨»œµƒpendingIntent,Œ™¡À∑¿÷π2.3º∞“‘œ¬∞Ê±æ±®¥Ì
     * @flags Ù–‘:
     * ‘⁄∂•≤ø≥£◊§:Notification.FLAG_ONGOING_EVENT
     * µ„ª˜»•≥˝£∫ Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
}
