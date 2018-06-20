package com.example.sasham.itmeans.favorites;


import com.example.sasham.itmeans.data.db.model.FavoriteWord;

import java.util.List;

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
        return realm.where(FavoriteWord.class).findAll().sort(FavoriteWord.TIME_STAMP_FIELD, Sort.DESCENDING);
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

    @Override
    void dispose() {
        realm.close();
    }

    @Override
    void removeAllFavoriteWords() {
        if (realm.where(FavoriteWord.class).count() > 0) {
            realm.beginTransaction();
            realm.delete(FavoriteWord.class);
            realm.commitTransaction();
        }
    }
}
