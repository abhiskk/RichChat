package com.example.echattest1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class GetYoutubeThumbNail extends AsyncTask<String, Void, Bitmap> {

    ImageView _bmImage;

    public GetYoutubeThumbNail(ImageView bmImage) {

        _bmImage = bmImage;

    }

    @Override
    protected Bitmap doInBackground(final String... url) {

        Bitmap bitmap = null;

        Log.e("here1 ","test");

        try {

            InputStream in = new java.net.URL(url[0]).openStream();
            bitmap = BitmapFactory.decodeStream(in);

        }   catch (Exception e) {

            Log.e("Error11 ", e.getMessage());

        }

        Log.e("here2 ","test");

        return bitmap;

    }

    protected void onPostExecute(Bitmap result) {
        _bmImage.setImageBitmap(result);
    }

}
