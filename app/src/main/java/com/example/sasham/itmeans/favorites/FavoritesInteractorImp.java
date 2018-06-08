package com.example.sasham.itmeans.favorites;


import com.example.sasham.itmeans.data.network.TwinWordApi;
import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.Sort;

public class FavoritesInteractorImp extends FavoritesInteractor {
    private static final String TAG = FavoritesInteractorImp.class.getSimpleName();
    private final Realm realm;

    public FavoritesInteractorImp(Realm realm) {
        this.realm = realm;
    }

    @Override
    List<FavoriteWord> allFavoriteWords() {
        return realm.where(FavoriteWord.class).findAll().sort("timeStamp", Sort.DESCENDING);
    }

    @Override
    void delete(FavoriteWord favoriteWord) {
        if (favoriteWord.isManaged()) {
            favoriteWord.deleteFromRealm();
        }
    }

    @Override
    void create(FavoriteWord favoriteWord) {
        if (!favoriteWord.isManaged()) {
            realm.copyToRealm(favoriteWord);
        }
    }
}
