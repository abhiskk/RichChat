package com.example.EChat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.example.EChatLibrary.*;

public class MyActivity extends  Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setText("random url : http://www.flickr.com/photos/35972709@N03/8079198551/ something http://www.flickr.com/photos/fcnikon/9127536772 .");
    }

    public void previewUrl(View v) {
        EditText editText = (EditText) findViewById(R.id.editText1);
        String tempText = editText.getText().toString();
        RichChat richChat = new RichChat(this,tempText);
        LinearLayout imageLayout = (LinearLayout) findViewById(R.id.imageLayout);
        imageLayout.addView(richChat);
    }

}
