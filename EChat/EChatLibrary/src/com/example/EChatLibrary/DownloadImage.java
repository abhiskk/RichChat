package com.example.EChatLibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class DownloadImage {

    final int THUMBNAIL_WIDTH = 150;
    final int THUMBNAIL_HEIGHT = 150;

    DownloadImage () {
    }

    public Bitmap download(String url) throws IOException{
        Bitmap bitmap;
        InputStream in = new java.net.URL(url).openStream();
        bitmap = BitmapFactory.decodeStream(in);
        return Bitmap.createScaledBitmap(bitmap,THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT,true);
    }

}
