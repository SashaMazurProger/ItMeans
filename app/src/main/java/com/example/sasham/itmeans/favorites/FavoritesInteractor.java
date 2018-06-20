package com.example.sasham.itmeans.favorites;


import com.example.sasham.itmeans.data.db.model.FavoriteWord;

import java.util.List;

/**
 * Created by Sasha M on 14.04.2018.
 */

public abstract class FavoritesInteractor {
    abstract List<FavoriteWord> allFavoriteWords();
    abstract void delete(FavoriteWord favoriteWord);
    abstract void create(FavoriteWord favoriteWord);
    abstract void dispose();
    abstract void removeAllFavoriteWords();
}
