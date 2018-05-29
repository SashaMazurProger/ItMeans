package com.example.sasham.itmeans.util;

import android.support.v7.widget.SearchView;
import android.widget.EditText;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxUtils {

    public static Observable<String> textInputObservable(SearchView searchView) {

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }
}
