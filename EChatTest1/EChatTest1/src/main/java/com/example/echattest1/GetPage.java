package com.example.echattest1;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetPage extends AsyncTask<Void, Void, String> {

    public GetPage() {

    }

    @Override
    protected String doInBackground(final Void... params) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("http://www.google.com");

        try {

            HttpResponse response = httpClient.execute(httpGet, localContext);

            String result = "";

            String line;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            response.getEntity().getContent()
                    )
            );

            while(( line = reader.readLine() ) != null) {
                result += line + "\n";
            }

            Log.e("Logcat ", "lite1 " + result.length());

            return result;


        }   catch (Exception e) {

            Log.e("Logcat ", "lite2");

        }

        return null;

    }

}