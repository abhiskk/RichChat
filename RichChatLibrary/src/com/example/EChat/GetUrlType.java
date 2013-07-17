package com.example.EChat;

public class GetUrlType {

    public GetUrlType(){
    }

    public GetThumbNail.UrlType getType(String url) {
        if( isYoutube(url) )
            return GetThumbNail.UrlType.YOUTUBE;
        if( isFlickr(url) )
            return GetThumbNail.UrlType.FLICKR;
        if( isInstagram(url) )
            return GetThumbNail.UrlType.INSTAGRAM;
        return GetThumbNail.UrlType.OTHERS;
    }

    boolean isYoutube(String url) {
        String regex = "(https?://)?(www[.])?(((youtube[.]com)|(y2u.be)|(youtu.be))/[-A-Za-z0-9+&@#/%?=~_()|!:./;]*)";
        return url.matches(regex);
    }

    boolean isFlickr(String url) {
        String regex = "(https?://)?(www[.])?((flickr.com)/[-A-Za-z0-9+&@#/%?=~_()|!:./;]*)";
        return url.matches(regex);
    }

    boolean isInstagram(String url) {
        String regex = "(https?://)?(www[.])?((instagram.com)|(instagr.am))/p/[-A-Za-z0-9+&@#/%?=~_()|!:./;]*";
        return url.matches(regex);
    }

}
