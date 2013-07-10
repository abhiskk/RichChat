package com.example.EChatLibrary;

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
        ArrayList<ThumbnailInfo> previewLinks = new ArrayList<ThumbnailInfo>();

        while (urlMatcher.find())
            previewLinks.add(get(urlMatcher));

        return previewLinks;

    }

    ThumbnailInfo get(Matcher urlMatcher) {
        String urlStr = urlMatcher.group();
        if (urlStr.startsWith("(") && urlStr.endsWith(")"))
            urlStr = urlStr.substring(1,urlStr.length()-1);
        return new ThumbnailInfo(urlStr);
    }

}
