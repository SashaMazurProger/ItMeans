package com.example.sasham.itmeans.util;

import com.example.sasham.itmeans.data.db.model.RecentWord;

import java.util.List;

public class DataUtils {

    public static String concatRecentWords(List<RecentWord> recentWords){
        StringBuilder stringBuilder=new StringBuilder();
        for(RecentWord recentWord:recentWords){
            stringBuilder.append(recentWord.getEntry());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }
}
