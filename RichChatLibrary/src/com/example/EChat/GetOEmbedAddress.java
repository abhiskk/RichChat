package com.example.EChat;

public class GetOEmbedAddress {

    String url;
    GetThumbNail.UrlType urlType;

    public GetOEmbedAddress(String _url,GetThumbNail.UrlType _urlType) {
        url=_url;
        urlType=_urlType;
    }

    public String getAddress() {
        switch (urlType) {
            case YOUTUBE:
                return youtubeAddress();
            case FLICKR:
                return flickrAddress();
            case INSTAGRAM:
                return instagramAddress();
            case OTHERS:
                return null;
        }
        return null;
    }

    String youtubeAddress() {
        return "http://www.youtube.com/oembed?url=http%3A//youtube.com/watch%3Fv%3D"+getYoutubeId()+"&format=json";
    }

    String flickrAddress() {
        return "http://flickr.com/services/oembed?url="+url+"&format=json";
    }

    String instagramAddress() {
        return "http://api.instagram.com/oembed?url="+url;
    }

    String getYoutubeId() {
        if(url.toLowerCase().contains("youtube")){
            int ind=-1;
            for(int i=0;i<url.length()-1;i++)
                if(url.charAt(i)=='v' && url.charAt(i+1)=='=')
                {
                    ind=i+2;
                    break;
                }
            if(ind==-1)
                return null;
            int ind2=ind;
            while(ind2<url.length() && url.charAt(ind2)!='&')
                ind2++;
            String ans;
            ans=url.substring(ind,ind2);
            return ans;
        } else {
            int ind=url.length()-1;
            while(ind>=0 && url.charAt(ind)!='/')
                ind--;
            if(ind==0)
                return null;
            int ind2=ind;
            while(ind2<url.length() && url.charAt(ind2)!='&')
                ind2++;
            return url.substring(ind+1,ind2);
        }
    }

}
