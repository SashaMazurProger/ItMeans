package com.example.sasham.itmeans.search;


import com.example.sasham.itmeans.data.network.NetworkRepository;
import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.db.model.FavoriteWord;
import com.example.sasham.itmeans.data.db.model.RecentWord;
import com.example.sasham.itmeans.data.network.response.WordExample;
import com.example.sasham.itmeans.data.network.response.WordReference;
import com.example.sasham.itmeans.data.network.response.WordTheme;

import io.reactivex.Observable;

/**
 * Created by Sasha M on 14.04.2018.
 */

public interface WordDetailsInteractor extends NetworkRepository{
    void delete(FavoriteWord favoriteWord);
    void create(FavoriteWord favoriteWord);
    void create(RecentWord recentWord);
    FavoriteWord findByName(String entry);
    void dispose();
}
