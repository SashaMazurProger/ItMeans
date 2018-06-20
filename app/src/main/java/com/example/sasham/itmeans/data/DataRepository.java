package com.example.sasham.itmeans.data;

import com.example.sasham.itmeans.Constants;
import com.example.sasham.itmeans.data.network.TwinWordApi;
import com.example.sasham.itmeans.data.network.response.WordAssociation;
import com.example.sasham.itmeans.data.network.response.WordDefinition;
import com.example.sasham.itmeans.data.network.response.WordExample;
import com.example.sasham.itmeans.data.network.response.WordReference;
import com.example.sasham.itmeans.data.network.response.WordTheme;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class DataRepository {

    private TwinWordApi twinWordApi;
    private Realm realm;

    public DataRepository(TwinWordApi twinWordApi, Realm realm) {
        this.twinWordApi = twinWordApi;
        this.realm = realm;
    }

    public Observable<WordAssociation> getWordAssociation(String word) {
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

    public Observable<WordDefinition> getWordDefinition(String word) {
        return twinWordApi.getWordDefinition(word)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(definition -> {

                    realm.executeTransaction(r ->
                            r.insert(definition));

                    return Observable.just(definition);
                })
                .onErrorResumeNext(throwable -> {
                    WordDefinition definition = realm
                            .where(WordDefinition.class)
                            .equalTo(Constants.WORD_ENTRY_FIELD, word)
                            .findFirst();

                    return Observable.just(definition);
                });
    }

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

    public Observable<WordReference> getWordReference(String word) {
        return twinWordApi.getWordReference(word)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((wordReference -> {
                    realm.executeTransaction(r ->
                            r.insert(wordReference));

                    return Observable.just(wordReference);
                }))
                .onErrorResumeNext((throwable) -> {
                    WordReference reference = realm
                            .where(WordReference.class)
                            .equalTo(Constants.WORD_ENTRY_FIELD, word)
                            .findFirst();

                    return Observable.just(reference);

                });
    }

    public Observable<WordTheme> getWordTheme(String word) {
        return twinWordApi.getWordTheme(word)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((wordTheme -> {
                    realm.executeTransaction(r ->
                            r.insertOrUpdate(wordTheme));

                    return Observable.just(wordTheme);
                }))
                .onErrorResumeNext((throwable) -> {
                    WordTheme wordTheme = realm
                            .where(WordTheme.class)
                            .equalTo(Constants.WORD_ENTRY_FIELD, word)
                            .findFirst();

                    return Observable.just(wordTheme);

                });
    }

    public <T extends RealmObject> Observable<Boolean> delete(T item) {
        if (item.isValid()) {
            realm.executeTransaction((r) -> {
                item.deleteFromRealm();
            });
            return Observable.just(true);
        }
        return Observable.just(false);
    }

    public <T extends RealmObject> Observable<Boolean> create(T item) {
        if (!item.isValid()) {
            realm.executeTransaction((r) -> {
                r.insertOrUpdate(item);
            });
            return Observable.just(true);
        }
        return Observable.just(false);
    }

    public <T extends RealmObject> Observable<Boolean> exists(T item) {
        return Observable.just(item.isValid());
    }

    public <T extends RealmObject> Flowable<RealmResults<T>> getAll(Class<T> clazz) {
        return realm.where(clazz)
                .findAll()
                .asFlowable();
    }

    public void destroy() {
        realm.close();
    }
}
