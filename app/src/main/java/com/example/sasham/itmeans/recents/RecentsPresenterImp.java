package com.example.sasham.itmeans.recents;

import android.util.Log;

import com.example.sasham.itmeans.data.network.db.RecentWord;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;

public class RecentsPresenterImp implements RecentsPresenter {

    private RecentsView recentsView;
    private Realm realm;

    @Inject
    public RecentsPresenterImp(RecentsView recentsView, Realm realm) {
        this.recentsView = recentsView;
        this.realm = realm;
    }

    @Override
    public void fetchWords() {
        Log.d(getClass().getSimpleName(), "fetchWords: count:"+realm.where(RecentWord.class).count());

        recentsView.showWords(
                realm.where(RecentWord.class)
                        .findAll()
                        .sort(RecentWord.TIME_STAMP_FIELD, Sort.DESCENDING));
//        recentsView.showWords(Arrays.asList(
//                new RecentWord("ha",new Date().getTime()),
//                new RecentWord("hi",new Date().getTime()),
//                new RecentWord("ho",new Date().getTime())
//        )

    }

    @Override
    public void onDestroy() {

    }
}
