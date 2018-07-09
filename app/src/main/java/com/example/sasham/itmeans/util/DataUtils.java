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
        if(stringBuilder.length()>0){
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        return stringBuilder.toString();
    }
}
