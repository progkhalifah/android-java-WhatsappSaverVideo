package com.progkhalifah.appwhatsappsavervideo.model;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.webkit.CookieManager;

import static android.content.Context.DOWNLOAD_SERVICE;

public class Constant {

    public static final String FOLDER_NAME = "/WhatsApp/";
    public static final String SAVE_FOLDER_NAME = "/WhatsApp Saver Video/";

    public static String RootDirectoryFacebook = "/My story saver/facebook/";

    public static void download(String downloadPath, String destinationPath, Context context, String fillname, String getUrl){
        /*Toast.makeText(context, "Downloading Started", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(fillname);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath+fillname);
        ((DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);*/

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadPath));

//        String title = URLUtil.guessFileName(getUrl, null, null);
        request.setTitle(fillname);
        request.setDescription("Downloading File please wait");
        String cookie = CookieManager.getInstance().getCookie(getUrl);
        request.addRequestHeader("cookie", cookie);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath+fillname);

        DownloadManager downloadManager = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);







    }




}
