package com.example.echattest1;

// for holding the thumbnail information

public class ThumbnailInfo {

    public String imageURL;
    public String enteredURL;
    public Integer position;

    public ThumbnailInfo(String _url,String _url2,Integer _position) {
        imageURL=_url;
        enteredURL=_url2;
        position=_position;
    }

}
