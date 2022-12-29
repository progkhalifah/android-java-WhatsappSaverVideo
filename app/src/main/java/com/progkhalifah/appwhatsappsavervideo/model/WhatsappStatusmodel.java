package com.progkhalifah.appwhatsappsavervideo.model;

import android.net.Uri;

public class WhatsappStatusmodel {
    private String name, path, filename;
    private Uri uri;

    public WhatsappStatusmodel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
