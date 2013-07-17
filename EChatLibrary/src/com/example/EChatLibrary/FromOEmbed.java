package com.example.EChatLibrary;

import android.graphics.Bitmap;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class FromOEmbed {

    JSONObject json;
    public FromOEmbed(String url) throws IOException, JSONException {
        json = readJSONFromUrl(url);
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJSONFromUrl(String url) throws IOException,JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public Bitmap getImage() throws IOException, JSONException {
        DownloadImage downloadImage = new DownloadImage();
        return downloadImage.download(json.get("thumbnail_url").toString());
    }

    public String getTitle() throws IOException, JSONException {
        return json.get("title").toString();
    }

}
