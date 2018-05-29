package com.example.sasham.itmeans.data.network;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.WordDefinition;
import com.example.sasham.itmeans.data.WordRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetworkWordRepository implements WordRepository {
    private static final String TAG = NetworkWordRepository.class.getSimpleName();

    private WordAssociation resWordAssociation;

    @Inject
    public NetworkWordRepository() {
    }

    @Override
    public Observable<WordAssociation> getWordAssociation(final String word) {

        return NetworkModule.getTwinWordWebService()
                .getWordAssociation(word);

    }

    @Override
    public Observable<WordDefinition> getWordDefinition(String word) {
        return NetworkModule.getTwinWordWebService()
                .getWordDefinition(word);
    }
}
