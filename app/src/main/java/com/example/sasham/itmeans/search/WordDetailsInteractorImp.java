package com.example.sasham.itmeans.search;


import com.example.sasham.itmeans.Constants;
import com.example.sasham.itmeans.data.DataRepository;
import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.network.TwinWordApi;
import com.example.sasham.itmeans.data.db.model.FavoriteWord;
import com.example.sasham.itmeans.data.db.model.RecentWord;
import com.example.sasham.itmeans.data.network.response.WordExample;
import com.example.sasham.itmeans.data.network.response.WordReference;
import com.example.sasham.itmeans.data.network.response.WordTheme;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.realm.Realm;

public class WordDetailsInteractorImp implements WordDetailsInteractor {
    private static final String TAG = WordDetailsInteractorImp.class.getSimpleName();
    private final TwinWordApi twinWordApi;
    private Realm realm;
    private DataRepository dataRepository;

    public WordDetailsInteractorImp(TwinWordApi twinWordApi, Realm realm, DataRepository dataRepository) {
        this.twinWordApi = twinWordApi;
        this.realm = realm;
        this.dataRepository = dataRepository;
    }

    @Override
    public Observable<WordAssociation> getWordAssociation(final String word) {
        return twinWordApi.getWordAssociation(word)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(association -> {

                    realm.executeTransaction(r ->
                            r.insert(association));

                    return Observable.just(association);
                })
                .onErrorResumeNext(throwable -> {
                    WordAssociation association = realm
                            .where(WordAssociation.class)
                            .equalTo(Constants.WORD_ENTRY_FIELD, word)
                            .findFirstAsync();

                    return Observable.just(association);
                });

    }

    @Override
    public Observable<WordDefinition> getWordDefinition(final String word) {
//        return twinWordApi.getWordDefinition(word)
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(definition -> {
//
//                    realm.executeTransaction(r ->
//                            r.insert(definition));
//
//                    return Observable.just(definition);
//                })
//                .onErrorResumeNext(throwable -> {
//                    WordDefinition definition = realm
//                            .where(WordDefinition.class)
//                            .equalTo(Constants.WORD_ENTRY_FIELD, word)
//                            .findFirst();
//
//                    return Observable.just(definition);
//                });
        return dataRepository.getWordDefinition(word);
    }

    @Override
    public Observable<WordExample> getWordExample(String word) {
        return twinWordApi.getWordExample(word)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((wordExample -> {
                    realm.executeTransaction(r ->
                            r.insert(wordExample));

                    return Observable.just(wordExample);
                }))
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends WordExample>>() {
                    @Override
                    public ObservableSource<? extends WordExample> apply(Throwable throwable) throws Exception {
                        WordExample example = realm
                                .where(WordExample.class)
                                .equalTo(Constants.WORD_ENTRY_FIELD, word)
                                .findFirst();

                        return Observable.just(example);
                    }
                });
    }

    @Override
    public Observable<WordReference> getWordReference(String word) {
        return null;
    }

    @Override
    public Observable<WordTheme> getWordTheme(String word) {
        return null;
    }

    @Override
    public void delete(FavoriteWord favoriteWord) {
        if (favoriteWord.isManaged()) {
            realm.beginTransaction();
            favoriteWord.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Override
    public void create(FavoriteWord favoriteWord) {
        if (!favoriteWord.isManaged()) {
            realm.beginTransaction();
            realm.copyToRealm(favoriteWord);
            realm.commitTransaction();
        }
    }

    @Override
    public void create(RecentWord recentWord) {
        if (!recentWord.isManaged()) {
            realm.beginTransaction();
            realm.copyToRealm(recentWord);
            realm.commitTransaction();
        }
    }

    @Override
    public FavoriteWord findByName(String entry) {
        return realm.where(FavoriteWord.class)
                .equalTo(FavoriteWord.ENTRY_FIELD, entry)
                .findFirst();

    }

    @Override
    public void dispose() {
        realm.close();
    }
}


































