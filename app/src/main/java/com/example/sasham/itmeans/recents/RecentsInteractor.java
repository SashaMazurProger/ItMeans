package com.example.sasham.itmeans.recents;

import com.example.sasham.itmeans.data.db.model.RecentWord;

import java.util.List;

public interface RecentsInteractor {

    List<RecentWord> getAllRecentWords();
    void deleteAllRecentWords();
    void destroy();
}
