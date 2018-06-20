package com.example.sasham.itmeans.recents;

import com.example.sasham.itmeans.data.db.model.RecentWord;

import java.util.List;

public interface RecentsView {

    void showWords(List<RecentWord> words);
    void onLoading();
    void onLoaded();
}
