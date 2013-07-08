package com.example.EChatLibrary;

import android.graphics.Bitmap;

public class ThumbNailViewInfo {

    public Bitmap imageView;
    public String textViewTitle;
    public String textViewDescription;

    public ThumbNailViewInfo(Bitmap _imageView,String _textViewTitle,String _textViewDescription) {
        imageView=_imageView;
        textViewTitle=_textViewTitle;
        textViewDescription=_textViewDescription;
    }

}
