package com.example.EChatLibrary;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GetThumbNail extends AsyncTask<String, Void, ThumbnailViewInfo> {

    ImageView _bmImage;
    TextView _titleTextView;
    TextView _descriptionTextView;
    View _childView;
    LinearLayout _imageLayout;

    public enum UrlType { YOUTUBE,FLICKR,INSTAGRAM,OTHERS }

    public GetThumbNail(LinearLayout imageLayout,View childView) {
        _imageLayout = imageLayout;
        _childView = childView;
        _bmImage = (ImageView) _childView.findViewById(R.id.image1);
        _titleTextView = (TextView) _childView.findViewById(R.id.text1);
        _descriptionTextView = (TextView) _childView.findViewById(R.id.text2);
    }

    @Override
    protected ThumbnailViewInfo doInBackground(final String... url) {
        //check here whether oembed can be used or not and if it can be used whether the information is received or not

        GetUrlType getUrlType = new GetUrlType();
        String oEmbedAddress="";

        UrlType urlType=getUrlType.getType(url[0]);

        if(urlType!=UrlType.OTHERS)
        {
            oEmbedAddress = new GetOEmbedAddress(url[0],getUrlType.getType(url[0])).getAddress();
        }

        if(oEmbedAddress!=null) {
            if(urlType==UrlType.INSTAGRAM)  {
                try {
                    FromOEmbed fromOEmbed = new FromOEmbed(oEmbedAddress);
                    Bitmap bitmap = new DownloadImage().download(url[0]+"media/?size=t");
                    String title=fromOEmbed.getTitle();
                    return new ThumbnailViewInfo(bitmap,title,"");
                }   catch (Exception e) {
                    Log.e("temp3 ",e.getMessage());
                }
            }
            else    {
                try{
                    FromOEmbed fromOEmbed = new FromOEmbed(oEmbedAddress);
                    Bitmap bitmap=fromOEmbed.getImage();
                    String title=fromOEmbed.getTitle();
                    return new ThumbnailViewInfo(bitmap,title,"");
                } catch (Exception e) {
                    Log.e("temp2 ",e.getMessage());
                }
            }
        }

        // fetching information from url source code

        try {
            FromPagesource fromPagesource = new FromPagesource(url[0]);
            if(!fromPagesource.checkResponse())
                return null;

            String title = fromPagesource.getTitle();
            String description = fromPagesource.getDescription();
            Bitmap bitmap = fromPagesource.getImage();
            if(bitmap!=null)
                return new ThumbnailViewInfo(bitmap,title,description);
            return null;
        } catch (Exception e) {
            Log.e("Logcat ", e.getMessage());
        }

        return null;

    }

    protected void onPostExecute(ThumbnailViewInfo result) {
        if(result==null)
            return;

        _bmImage.setImageBitmap(result.imageView);
        _titleTextView.setText(result.textViewTitle);
        _descriptionTextView.setText(result.textViewDescription);
        _imageLayout.addView(_childView);
    }

}