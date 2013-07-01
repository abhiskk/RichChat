package com.example.echattest1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class GetThumbNail extends AsyncTask<String, Void, Bitmap> {

    ImageView _bmImage;

    Context _context;

    public GetThumbNail(Context context,ImageView bmImage) {

        _context = context;
        _bmImage = bmImage;

    }

    @Override
    protected Bitmap doInBackground(final String... url) {

        Bitmap bitmap = null;

        try {

            InputStream in = new java.net.URL(url[0]).openStream();
            bitmap = BitmapFactory.decodeStream(in);

        }   catch (Exception e) {

            Log.e("Error11 ", e.getMessage());

        }

        return bitmap;

    }

    protected void onPostExecute(Bitmap result) {
        _bmImage.setImageBitmap(result);
    }

}
