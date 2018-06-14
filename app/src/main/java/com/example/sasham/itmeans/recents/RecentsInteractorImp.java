package com.example.sasham.itmeans.recents;

import com.example.sasham.itmeans.data.network.db.RecentWord;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;

public class RecentsInteractorImp implements RecentsInteractor {

    private Realm realm;

    @Inject
    public RecentsInteractorImp(Realm realm) {
        this.realm = realm;
    }


    @Override
    public List<RecentWord> getAllRecentWords() {
        return realm.where(RecentWord.class)
                .findAll()
                .sort(RecentWord.TIME_STAMP_FIELD, Sort.DESCENDING);
    }

    @Override
    public void deleteAllRecentWords() {
        if (realm.where(RecentWord.class).count() > 0) {
            realm.beginTransaction();
            realm.delete(RecentWord.class);
            realm.commitTransaction();
        }
    }

    @Override
    public void destroy() {
        realm.close();
    }
}
