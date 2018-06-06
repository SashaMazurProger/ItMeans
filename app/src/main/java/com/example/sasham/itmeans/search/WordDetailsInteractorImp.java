package com.example.sasham.itmeans.search;


import com.example.sasham.itmeans.data.network.WordAssociation;
import com.example.sasham.itmeans.data.network.WordDefinition;
import com.example.sasham.itmeans.data.network.TwinWordApi;

import io.reactivex.Observable;

public class WordDetailsInteractorImp implements WordDetailsInteractor {
    private static final String TAG = WordDetailsInteractorImp.class.getSimpleName();
    private final TwinWordApi twinWordApi;

    private WordAssociation resWordAssociation;

    public WordDetailsInteractorImp(TwinWordApi twinWordApi) {
        this.twinWordApi = twinWordApi;
    }

    @Override
    public Observable<WordAssociation> getWordAssociation(final String word) {

        return twinWordApi.getWordAssociation(word);

    }

    @Override
    public Observable<WordDefinition> getWordDefinition(String word) {
        return twinWordApi.getWordDefinition(word);
    }
}
