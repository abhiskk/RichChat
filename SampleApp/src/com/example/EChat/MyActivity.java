package com.example.EChat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

public class MyActivity extends  Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setAutoLinkMask(1);
        editText.setText("testing1 http://www.youtube.com/watch?v=J9rBIKHJoaY http://www.flickr.com/photos/delobbo/8065539051/");
    }

    public void previewUrl(View v) {
        EditText editText = (EditText) findViewById(R.id.editText1);
        String tempText = editText.getText().toString();
        LinearLayout imageLayout = (LinearLayout) findViewById(R.id.imageLayout);

        RichChat richChat = new RichChat(this,tempText);
        imageLayout.addView(richChat);
    }

}
