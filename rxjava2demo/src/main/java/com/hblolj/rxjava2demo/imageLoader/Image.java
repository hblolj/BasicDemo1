package com.hblolj.rxjava2demo.imageLoader;

import android.graphics.Bitmap;

/**
 * Created by hblolj on 2017/4/16.
 */

public class Image {

    private String url;
    private Bitmap bitmap;

    public Image() {
    }

    public Image(String url, Bitmap bitmap) {
        this.url = url;
        this.bitmap = bitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
