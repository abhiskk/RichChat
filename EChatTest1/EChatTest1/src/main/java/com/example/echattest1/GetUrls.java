package com.example.echattest1;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetUrls {

    public GetUrls() {

    }

    public ArrayList<ThumbnailInfo> extract(String input) {

        String urlRegex = "\\(?\\b(https?://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";

        Pattern urlPattern = Pattern.compile(urlRegex);
        Matcher urlMatcher = urlPattern.matcher(input);

        ArrayList<ThumbnailInfo> images = new ArrayList<ThumbnailInfo>();

        YoutubeChecker youtubeChecker = new YoutubeChecker();

        while ( urlMatcher.find() ) {
            String urlStr = urlMatcher.group();
            if(urlStr.startsWith("(") && urlStr.endsWith(")") )
                urlStr = urlStr.substring(1,urlStr.length()-1);
            if(youtubeChecker.valid(urlStr)) {
                images.add(new ThumbnailInfo(youtubeChecker.getId(urlStr),urlMatcher.end()));
            }
        }

        return images;

    }

}
