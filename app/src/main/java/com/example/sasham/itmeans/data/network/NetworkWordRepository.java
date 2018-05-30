package com.example.sasham.itmeans.data.network;

import android.util.Log;

import com.example.sasham.itmeans.data.WordAssociation;
import com.example.sasham.itmeans.data.WordDefinition;
import com.example.sasham.itmeans.data.WordRepository;
import com.example.sasham.itmeans.domain.DaggerWordUseCaseComponent;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetworkWordRepository implements WordRepository {
    private static final String TAG = NetworkWordRepository.class.getSimpleName();
    private final TwinWordApi twinWordApi;

    private WordAssociation resWordAssociation;

    @Inject
    public NetworkWordRepository() {
        twinWordApi = DaggerTwinWordComponent.builder().build().getTwinWordApi();
        Log.d(TAG, "NetworkWordRepository: "+twinWordApi);
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
