package com.example.sasham.itmeans.search;


import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import io.reactivex.Observable;

/**
 * Created by Sasha M on 14.04.2018.
 */

public interface WordDetailsInteractor {
    Observable<WordAssociation> getWordAssociation(String word);
    Observable<WordDefinition> getWordDefinition(String word);
    void delete(FavoriteWord favoriteWord);
    void create(FavoriteWord favoriteWord);
    FavoriteWord findByName(String entry);

}
