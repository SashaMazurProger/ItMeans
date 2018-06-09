package com.example.sasham.itmeans.search;

import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class WordDetailsModule {

    @Provides
    public WordDetailsInteractor wordRepository(Realm realm,TwinWordApi twinWordApi){
        return new WordDetailsInteractorImp(twinWordApi,realm);
    }

    @Provides
    public WordDetailsViewModel.Factory factory(WordDetailsInteractor wordDetailsInteractor){
        return new WordDetailsViewModel.Factory(wordDetailsInteractor);
    }
}
