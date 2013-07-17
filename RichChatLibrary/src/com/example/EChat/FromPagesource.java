package com.example.EChat;

import android.graphics.Bitmap;
import android.util.Log;
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

//looks for the og:image tag

public class FromPagesource {

    HttpClient _httpClient;
    HttpContext _localContext;
    HttpGet _httpGet;
    HttpResponse _response;

    String _urlSource;

    Document _doc;

    public FromPagesource(String url) {
        _httpClient = new DefaultHttpClient();
        _localContext = new BasicHttpContext();
        _httpGet = new HttpGet(url);
        try{
            _response = _httpClient.execute(_httpGet,_localContext);
            _urlSource= EntityUtils.toString(_response.getEntity());
            _doc= Jsoup.parse(_urlSource);
        }   catch (Exception e) {
            Log.e("Logcat ",e.getMessage());
        }
    }

    public boolean checkResponse() {
        return _doc!=null;
    }

    public String getTitle() {
        org.jsoup.select.Elements titleElement;
        titleElement = _doc.select("meta[property=og:title]");
        if(titleElement.size()>0) {
            return titleElement.get(0).attr("content");
        }
        return "";
    }

    String getDescription() {
        org.jsoup.select.Elements despcriptionElement;
        despcriptionElement = _doc.select("meta[property=og:description]");
        if (despcriptionElement.size() > 0) {
            return despcriptionElement.get(0).attr("content");
        }
        return "";
    }

    Bitmap getImage() throws IOException {
        org.jsoup.select.Elements metaElements = _doc.select("meta[property=og:image]");
        String thumbNailURL;
        DownloadImage downloadImage = new DownloadImage();
        if(metaElements.size()>0) {
            thumbNailURL = metaElements.get(0).attr("content");
            return downloadImage.download(thumbNailURL);
        }
        return null;
    }

}
