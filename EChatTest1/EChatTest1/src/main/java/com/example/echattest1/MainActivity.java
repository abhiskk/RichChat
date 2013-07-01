package com.example.echattest1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideKeyBoardOnStart();
        EditText temp1 = (EditText)findViewById(R.id.editText1);
        temp1.setText("fnlkewnflnew http://www.youtube.com/watch?v=J9rBIKHJoaY fwlnflnwfe nflkwfelneflnw");
        temp1.setAutoLinkMask(1);
    }

    public void previewUrl (View v) {

        GetUrls getUrls = new GetUrls();

        EditText te3 = (EditText)findViewById(R.id.editText1);
        String tempText = te3.getText().toString();
        LinearLayout imageLayout = (LinearLayout) findViewById(R.id.imageLayout);
        imageLayout.removeAllViewsInLayout();
        ArrayList<ThumbnailInfo> te1 = getUrls.extract(tempText);

        TextView tempTextView = (TextView)findViewById(R.id.textView2);

        for(ThumbnailInfo i : te1) {

            ImageView iv =new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity= Gravity.LEFT;
            iv.setLayoutParams(lp);
            imageLayout.addView(iv);
            new GetThumbNail(this,iv).execute(i.url);
            tempTextView.setAutoLinkMask(1);
            tempTextView.setText(tempText);

        }

    }

    public void hideKeyBoardOnStart() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
