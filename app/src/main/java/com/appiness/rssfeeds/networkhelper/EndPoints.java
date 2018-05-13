package com.appiness.rssfeeds.networkhelper;


import com.appiness.rssfeeds.BuildConfig;

/**
 * Created by adhishlal on 11/05/18.
 */

public class EndPoints {

    public static final String assetBaseURL = BuildConfig.API_BASE_URL;
    public static final String headlines = "get_headlines.php?include=blogger&limit=30&sortby=title&order=a&format=json";



}
