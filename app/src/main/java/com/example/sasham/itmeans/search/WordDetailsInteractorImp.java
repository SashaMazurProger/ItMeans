package com.example.sasham.itmeans.search;


import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.TwinWordApi;
import com.example.sasham.itmeans.data.network.db.FavoriteWord;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

public class WordDetailsInteractorImp implements WordDetailsInteractor {
    private static final String TAG = WordDetailsInteractorImp.class.getSimpleName();
    private final TwinWordApi twinWordApi;
    private Realm realm;
    private WordAssociation resWordAssociation;

    public WordDetailsInteractorImp(TwinWordApi twinWordApi, Realm realm) {
        this.twinWordApi = twinWordApi;
        this.realm = realm;
    }

    @Override
    public Observable<WordAssociation> getWordAssociation(final String word) {

        return twinWordApi.getWordAssociation(word);

    }

    @Override
    public Observable<WordDefinition> getWordDefinition(String word) {
        return twinWordApi.getWordDefinition(word);
    }

    @Override
    public void delete(FavoriteWord favoriteWord) {
        if(favoriteWord.isManaged()){
            realm.beginTransaction();
            favoriteWord.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Override
    public void create(FavoriteWord favoriteWord) {
        if(!favoriteWord.isManaged()){
            realm.beginTransaction();
            realm.copyToRealm(favoriteWord);
            realm.commitTransaction();
        }
    }

    @Override
    public FavoriteWord findByName(String entry) {
        return realm.where(FavoriteWord.class)
                .equalTo(FavoriteWord.ENTRY_FIELD,entry)
                .findFirst();

    }
}
