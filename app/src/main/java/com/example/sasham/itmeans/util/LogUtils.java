package com.example.sasham.itmeans.util;

public class LogUtils {

    public static String getTag(Object object) {
        String tag = "";
        if (object != null) {
            tag = object.getClass().getSimpleName();
        }
        return tag;
    }
}
