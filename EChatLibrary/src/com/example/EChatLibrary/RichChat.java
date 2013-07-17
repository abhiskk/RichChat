package com.example.EChatLibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RichChat extends LinearLayout {

    public String chatText;
    public LinearLayout thumbnailLayout;

    public RichChat(Context context,AttributeSet attrs) {
        super(context,attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.RichChat, 0, 0);
        chatText=a.getString(R.styleable.RichChat_chatText);
        if(chatText!=null)
            chatText=a.getString(R.styleable.RichChat_chatText);
        a.recycle();
        inflateView(context);
    }

    public RichChat(Context context,String _chatText) {
        super(context,null);
        chatText=_chatText;
        inflateView(context);
    }

    void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rich_chat_view,this,true);
        TextView chatTextView = (TextView) findViewById(R.id.chatText);
        chatTextView.setAutoLinkMask(1);
        chatTextView.setText(chatText);
        thumbnailLayout = (LinearLayout)findViewById(R.id.thumbnails);
        populateThumbnails(context);
    }

    public void populateThumbnails(final Context context) {
        GetUrls getUrls = new GetUrls();
        ArrayList<ThumbnailInfo> te1 = getUrls.extract(chatText);

        for (final ThumbnailInfo thumbnailInfo : te1) {
            final String url = thumbnailInfo.enteredURL;
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View child = vi.inflate(R.layout.thumbnail_view,null);

            child.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                   }
            });
            new GetThumbNail(thumbnailLayout,child).execute(url);
        }
    }

}
