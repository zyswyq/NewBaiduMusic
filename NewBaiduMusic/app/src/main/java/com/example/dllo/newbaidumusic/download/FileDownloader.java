package com.example.dllo.newbaidumusic.download;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.dllo.newbaidumusic.tool.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dllo on 17/3/2.
 */

public class FileDownloader {
    private FileUtil fileUtil=null;
    public FileDownloader(){
        this.fileUtil=new FileUtil();
    }

    /**
     * 下载指定路径的文件，并写入到指定的位置
     * @param dirName
     * @param fileName
     * @param urlStr
     * @return 返回0表示下载成功，返回1表示文件已经在指定位置存在，返回2表示下载出错
     */
    public int downloadFile(String dirName,String fileName,String urlStr){
        if(fileUtil.isExist(dirName,fileName)){
            return 1;
        }
        File file=fileUtil.IS2SD(fileUtil.getIS(urlStr), dirName, fileName);
        if(file.length()==0  || file==null){
            return 2;
        }
        updateGallery(fileName);
        listFiles(file);
        return 0;
    }

    private void updateGallery(String filename)//filename是我们的文件全名，包括后缀哦
    {
        MediaScannerConnection.scanFile(App.getContext(),
                new String[] { filename }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {

                    }
                });
        App.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+filename)));
        Log.d("HAHAHAH", "file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename);

    }


    private void listFiles(File file) {
//MediaScannerConnection.scanFile(getApplicationContext(), new String[]{Environment.getExternalStorageDirectory().getAbsolutePath()}, null, null);
        File[] fs=file.listFiles();
        if(fs!=null){
            for(int i=0;i<fs.length;i++){
                if(fs[i]!=null){
                    if(fs[i].isDirectory()){
//System.out.println("+++++++++++++++++++++++++");
//System.out.println(fs[i].getAbsolutePath());
                        listFiles(fs[i]);
                    }else {
                        if(fs[i].getName().endsWith(".mp3")){
                            MediaScannerConnection.scanFile(App.getContext(), new String[]{fs[i].getAbsolutePath()}, null, null);
                            System.out.println(fs[i].getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    /**
     * 通过文件在服务器的URL地址，下载到文件内容
     * @param urlStr
     * @return
     */
    public String download(String urlStr){
        StringBuffer sb=new StringBuffer();
        String line=null;
        InputStream is=fileUtil.getIS(urlStr);
        //由于InputStream流不方便使用，包装成处理流
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        try {
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}