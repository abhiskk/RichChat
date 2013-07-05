package com.example.echattest1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.io.InputStream;

public class GetThumbNailSrcCode extends AsyncTask<String, Void, ThumbNailViewInfo> {

    ImageView _bmImage;
    TextView _titleTextView;
    TextView _descriptionTextView;
    View _childView;
    LinearLayout _imageLayout;

    public GetThumbNailSrcCode(LinearLayout imageLayout,View childView) {
        Log.e("Logcat ","later1");
        _imageLayout=imageLayout;
        _childView=childView;
        _bmImage=(ImageView)_childView.findViewById(R.id.image1);
        _titleTextView=(TextView)_childView.findViewById(R.id.text1);
        _descriptionTextView=(TextView)_childView.findViewById(R.id.text2);
    }

    @Override
    protected ThumbNailViewInfo doInBackground(final String... url) {

        Log.e("logcat ","later9999"+url);

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url[0]);

        try {
            HttpResponse response = httpClient.execute(httpGet,localContext);
            String temp = EntityUtils.toString(response.getEntity());
            Document doc;
            doc=Jsoup.parse(temp);

            String title="";

            org.jsoup.select.Elements titleElement;
            titleElement=doc.select("meta[property=og:title]");
            if(titleElement.size()>0) {
                title=titleElement.get(0).attr("content");
            }
            String description="";

            org.jsoup.select.Elements descriptionElement;
            descriptionElement=doc.select("meta[property=og:description]");
            if(descriptionElement.size()>0) {
                description=descriptionElement.get(0).attr("content");
            }

            org.jsoup.select.Elements metaElements;
            metaElements=doc.select("meta[property=og:image]");
            String thumbNailURL;
            if(metaElements.size()>0) {
                thumbNailURL=metaElements.get(0).attr("content");
                Bitmap bitmap;
                InputStream in = new java.net.URL(thumbNailURL).openStream();
                bitmap=BitmapFactory.decodeStream(in);
                return new ThumbNailViewInfo(Bitmap.createScaledBitmap(bitmap,200,200,true),title,description);
            }

            return null;

        }   catch (Exception e) {
            Log.e("Logcat2 ",e.getMessage());
        }

        return null;

    }

    protected void onPostExecute(ThumbNailViewInfo result) {
        if(result==null)
            return;
        _bmImage.setImageBitmap(result.imageView);
        _titleTextView.setText(result.textViewTitle);
        _descriptionTextView.setText(result.textViewDescription);
        _imageLayout.addView(_childView);
        Log.e("Logcat ","later2");
    }

}