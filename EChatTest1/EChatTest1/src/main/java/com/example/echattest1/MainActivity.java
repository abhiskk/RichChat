package com.example.echattest1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Logcat ", "temp11");
    }

    public void previewUrl (View v) {

        GetUrls getUrls = new GetUrls();

        ArrayList<ThumbnailInfo> te1 = getUrls.extract("fnlkewnflnew http://www.youtube.com/watch?v=j4dMnAPZu70 fwlnflnwfe nflkwfelneflnw");

        new GetThumbNail((ImageView) findViewById(R.id.thumbNail)).execute(te1.get(0).url);

    }

}
