package com.example.echattest1;


// This class checks if the url is a valid youtube link
// If link is valid it returns the url to get the image

public class YoutubeChecker {

    public YoutubeChecker() {

    }

    public boolean valid(String url) {
        String regex = "(https?://)?(www[.])?(((youtube[.]com)|(y2u.be)|(youtu.be))/[-A-Za-z0-9+&@#/%?=~_()|!:./;]*)";
        return url.matches(regex);
    }

    public String getId(String url) {
        if(url.toLowerCase().indexOf("youtube") >=0) {
            int ind=-1;
            for(int i=0;i<url.length()-1;i++)
                if(url.charAt(i)=='v' && url.charAt(i+1)=='=')
                    ind=i+2;
            if(ind==-1)
                return "";
            int ind2=ind;
            while(ind2<url.length() && url.charAt(ind2)!='&')
                ind2++;
            String ans="";
            if(ind!=-1)
                ans=url.substring(ind,ind2);
            if(!ans.equals(""))
                return "http://img.youtube.com/vi/"+ans+"/0.jpg";
            return ans;
        }
        else {
            int ind=url.length()-1;
            while(ind>=0 && url.charAt(ind)!='/')
                ind--;
            if(ind==0)
                return "";
            int ind2 = ind;
            while(ind2<url.length() && url.charAt(ind2)!='&')
                ind2++;
            String ans=url.substring(ind,ind2);
            return "http://img.youtube.com/vi"+ans+"/0.jpg";
        }
    }

}
