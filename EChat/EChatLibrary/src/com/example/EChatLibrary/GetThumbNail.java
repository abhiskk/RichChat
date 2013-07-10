package com.example.EChatLibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;

public class GetThumbNail extends AsyncTask<String, Void, ThumbnailViewInfo> {

    final int THUMBNAIL_WIDTH = 200;
    final int THUMBNAIL_HEIGHT = 200;

    ImageView _bmImage;
    TextView _titleTextView;
    TextView _descriptionTextView;
    View _childView;
    LinearLayout _imageLayout;
    Document doc;

    public GetThumbNail(LinearLayout imageLayout,View childView) {
        _imageLayout = imageLayout;
        _childView = childView;
        _bmImage = (ImageView) _childView.findViewById(R.id.image1);
        _titleTextView = (TextView) _childView.findViewById(R.id.text1);
        _descriptionTextView = (TextView) _childView.findViewById(R.id.text2);
    }

    @Override
    protected ThumbnailViewInfo doInBackground(final String... url) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url[0]);

        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);
            String temp = EntityUtils.toString(response.getEntity());

            doc = Jsoup.parse(temp);

            String title = getTitle();
            String description = getDescription();
            Bitmap bitmap = getImage();

            if(bitmap!=null)
                return new ThumbnailViewInfo(bitmap,title,description);
            return null;
        } catch (Exception e) {
            Log.e("Logcat2 ", e.getMessage());
        }

        return null;

    }

    protected void onPostExecute(ThumbnailViewInfo result) {
        if(result==null)
            return;
        _bmImage.setImageBitmap(result.imageView);
        _titleTextView.setText(result.textViewTitle);
        _descriptionTextView.setText(result.textViewDescription);
        _imageLayout.addView(_childView);
    }

    String getTitle() {
        org.jsoup.select.Elements titleElement;
        titleElement = doc.select("meta[property=og:title]");
        if(titleElement.size()>0) {
            return titleElement.get(0).attr("content");
        }
        return "";
    }

    String getDescription() {
        org.jsoup.select.Elements despcriptionElement;
        despcriptionElement = doc.select("meta[property=og:description]");
        if (despcriptionElement.size() > 0) {
            return despcriptionElement.get(0).attr("content");
        }
        return "";
    }

    Bitmap getImage() throws IOException{
        org.jsoup.select.Elements metaElements = doc.select("meta[property=og:image]");
        String thumbNailURL;
        if(metaElements.size()>0) {
            thumbNailURL = metaElements.get(0).attr("content");
            Bitmap bitmap;
            InputStream in = new java.net.URL(thumbNailURL).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            return Bitmap.createScaledBitmap(bitmap,THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT,true);
        }
        return null;
    }

}

//xml creation of RichChat left