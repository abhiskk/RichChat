package com.example.echattest1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("Logcat ", "temp11");
    }

    public void previewUrl (View v) {

        Log.e("Temp ","Error1");

        new GetYoutubeThumbNail((ImageView) findViewById(R.id.thumbNail)).execute("http://img.youtube.com/vi/J9rBIKHJoaY/0.jpg");

    }

}
