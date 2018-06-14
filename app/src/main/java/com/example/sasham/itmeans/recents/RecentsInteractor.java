package com.example.sasham.itmeans.recents;

import com.example.sasham.itmeans.data.network.db.RecentWord;

import java.util.List;

public interface RecentsInteractor {

    List<RecentWord> getAllRecentWords();
    void deleteAllRecentWords();
    void destroy();
}
