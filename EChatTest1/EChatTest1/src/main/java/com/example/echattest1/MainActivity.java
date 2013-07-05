package com.example.echattest1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideKeyBoardOnStart();
        EditText editText = (EditText)findViewById(R.id.editText1);
        editText.setText("random url : http://www.flickr.com/photos/fcnikon/9140536995/ something.");
        editText.setAutoLinkMask(1);
    }
    public void previewUrl (View v) {

        Log.e("Logcat ","later4");
        EditText editText = (EditText)findViewById(R.id.editText1);
        String tempText = editText.getText().toString();
        LinearLayout imageLayout = (LinearLayout) findViewById(R.id.imageLayout);
        imageLayout.removeAllViewsInLayout();

        GetUrls getUrls = new GetUrls();
        ArrayList<ThumbnailInfo> te1 = getUrls.extract(tempText); //gets all youtube urls from text

        TextView textView = (TextView)findViewById(R.id.textView2);
        textView.setAutoLinkMask(1);
        textView.setText(tempText);

        for(final ThumbnailInfo thumbnailInfo : te1) {
            if(thumbnailInfo.imageURL.equals(""))
                continue;
            addThumbNail(imageLayout,thumbnailInfo.imageURL,thumbnailInfo.enteredURL);
        }

    }

    public void hideKeyBoardOnStart() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void addThumbNail(LinearLayout imageLayout,String thumbNailSource,final String url) {
        LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = vi.inflate(R.layout.thumbnail_view,null);
        ImageView iv=(ImageView)child.findViewById(R.id.image1);
        TextView tv=(TextView)child.findViewById(R.id.text1);
        TextView tv2=(TextView)child.findViewById(R.id.text2);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;

        iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

//        tv.setText("Nothing");
        iv.setLayoutParams(lp);
//        new GetThumbNail(iv).execute(thumbNailSource);
        new GetThumbNailSrcCode(imageLayout,child).execute(url);
//        Log.e("Logcat ","here1"+" "+thumbNailSource);
//        View separatingView = new View(this);
//        separatingView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,5));
//        separatingView.setBackgroundColor(0xFF000000);
//        imageLayout.addView(child);
//        imageLayout.addView(separatingView);
    }

}
