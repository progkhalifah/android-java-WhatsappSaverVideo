package com.progkhalifah.appwhatsappsavervideo;

import android.os.Environment;

import java.io.File;

public class Util {

    public static File RootDirectorywhatsapp =
            new File(Environment.getExternalStorageDirectory()
            + "/Download/MyStorySaver/Whatsapp");



    public static void createFileFolder(){
        if (!RootDirectorywhatsapp.exists())
            RootDirectorywhatsapp.mkdir();
    }




}
