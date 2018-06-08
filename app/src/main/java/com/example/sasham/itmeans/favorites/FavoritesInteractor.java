package com.example.sasham.itmeans.favorites;


import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Sasha M on 14.04.2018.
 */

public abstract class FavoritesInteractor {
    abstract List<FavoriteWord> allFavoriteWords();
    abstract void delete(FavoriteWord favoriteWord);
    abstract void create(FavoriteWord favoriteWord);
    public interface OnChangeListener{}
}
