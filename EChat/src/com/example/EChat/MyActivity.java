package com.example.EChat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import com.example.EChatLibrary.*;

import java.util.ArrayList;

public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        hideKeyboardOnStart();
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setText("random url : http://www.flickr.com/photos/fcnikon/9140536995/ something.");
    }

    public void previewUrl(View v) {
        EditText editText = (EditText) findViewById(R.id.editText1);
        String tempText = editText.getText().toString();
        LinearLayout imageLayout = (LinearLayout) findViewById(R.id.imageLayout);
        imageLayout.removeAllViewsInLayout();

        GetUrls getUrls = new GetUrls();
        ArrayList<ThumbnailInfo> te1 = getUrls.extract(tempText);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setAutoLinkMask(1);
        textView.setText(tempText);

        for (final ThumbnailInfo thumbnailInfo : te1) {
            addThumbNail(imageLayout,thumbnailInfo.enteredURL);
        }
    }

    public void hideKeyboardOnStart() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void addThumbNail(LinearLayout imageLayout,final String url) {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = vi.inflate(R.layout.thumbnail_view,null);
        ImageView iv = (ImageView) child.findViewById(R.id.image1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;

        child.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        iv.setLayoutParams(lp);
        new GetThumbNail(imageLayout,child).execute(url);
    }

}
