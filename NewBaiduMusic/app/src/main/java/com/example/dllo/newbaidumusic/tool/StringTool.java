package com.example.dllo.newbaidumusic.tool;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 17/2/18.
 */

public class StringTool {

    public static String getUTf(String string){
        try {
            string = new String(string.getBytes("gbk"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }

}
