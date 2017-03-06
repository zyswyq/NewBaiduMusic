package com.example.dllo.newbaidumusic.tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by dllo on 17/3/4.
 */

public class BaseTools {
    /**
     * ªÒ»°µ±«∞”¶”√∞Ê±æ∫≈
     * @param context
     * @return version
     * @throws Exception
     */
    public static String getAppVersion(Context context) throws Exception {
        // ªÒ»°packagemanagerµƒ µ¿˝
        PackageManager packageManager = context.getPackageManager();
        // getPackageName() «ƒ„µ±«∞¿‡µƒ∞¸√˚£¨0¥˙±Ì «ªÒ»°∞Ê±æ–≈œ¢
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        String versionName = packInfo.versionName;
        return versionName;
    }

    /**
     * ªÒ»°µ±«∞œµÕ≥SDK∞Ê±æ∫≈
     */
    public static int getSystemVersion(){
		/*ªÒ»°µ±«∞œµÕ≥µƒandroid∞Ê±æ∫≈*/
        int version= android.os.Build.VERSION.SDK_INT;
        return version;
    }
}
