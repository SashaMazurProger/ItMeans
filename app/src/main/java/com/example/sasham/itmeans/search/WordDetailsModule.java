package com.example.sasham.itmeans.search;

import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class WordDetailsModule {

    @DetailsScope
    @Provides
    public WordDetailsInteractor wordRepository(TwinWordApi twinWordApi, Realm realm) {
        return new WordDetailsInteractorImp(twinWordApi, realm);
    }

}
