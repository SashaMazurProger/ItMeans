package com.example.sasham.itmeans.data.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sasha M on 15.04.2018.
 */

public class BuildConfig {
    public static final String TWIN_WORD_BASE_URL = "https://twinword-word-graph-dictionary.p.mashape.com/";
    public static final String TWIN_WORD_MASHAPE_KEY = "064Thzu3FSmshlxruVSEfurK5wzrp143wTojsnDCHCJ4Zu2iwr";
    public static final String TWIN_WORD_MASHAPE_KEY_HEADER = "X-Mashape-Key";
    public static final String TWIN_WORD_ACCEPT_HEADER = "Accept";
    public static final String TWIN_WORD_ACCEPT_VALUE = "application/json";

//    public static Map<String, String> getTwinWordHeaders() {
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put(BuildConfig.TWIN_WORD_MASHAPE_KEY_HEADER,
//                BuildConfig.TWIN_WORD_MASHAPE_KEY);
//
//        headers.put(BuildConfig.TWIN_WORD_ACCEPT_HEADER,
//                BuildConfig.TWIN_WORD_ACCEPT_VALUE);
//
//        return headers;
//    }
}
