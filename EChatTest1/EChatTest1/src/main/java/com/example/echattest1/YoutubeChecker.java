package com.example.echattest1;

public class YoutubeChecker {

    public YoutubeChecker() {

    }

    public boolean valid(String url) {
        String regex = "(https?://)?(www[.])?(((youtube[.]com)|(y2u.be)|(youtu.be))/[-A-Za-z0-9+&@#/%?=~_()|!:./;]*)";
        return url.matches(regex);
    }

    public String getId(String url) {
        int ind=-1;
        for(int i=0;i<url.length()-1;i++)
            if(url.charAt(i)=='v' && url.charAt(i+1)=='=')
                ind=i+2;
        String ans="";
        if(ind!=-1)
            ans=url.substring(ind);
        ans="http://img.youtube.com/vi/"+ans+"/0.jpg";
        return ans;
    }

}
