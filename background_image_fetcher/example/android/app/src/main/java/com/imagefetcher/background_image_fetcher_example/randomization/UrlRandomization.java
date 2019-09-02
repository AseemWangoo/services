package com.imagefetcher.background_image_fetcher_example.randomization;

import java.util.Random;

public class UrlRandomization {

    private static final int BASE = 100;

    private static final String URL = "https://picsum.photos/id/";

    private static final String SIZE = "/600/800";

    public static String[] randomURLs() {

        String url1 = URL + "197" + SIZE;
        String url2 = URL + "196" + SIZE;
        String url3 = URL + "198" + SIZE;
        String url4 = URL + "199" + SIZE;

        String[] randomUrls = {url1, url2, url3, url4};

        return randomUrls;
    }
}
