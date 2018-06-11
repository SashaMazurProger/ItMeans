package com.example.sasham.itmeans.search;

import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class WordDetailsModule {

    @DetailsScope
    @Provides
    public WordDetailsInteractor wordRepository(Realm realm,TwinWordApi twinWordApi){
        return new WordDetailsInteractorImp(twinWordApi,realm);
    }

    @DetailsScope
    @Provides
    public WordDetailsViewModel.Factory factory(WordDetailsInteractor wordDetailsInteractor){
        return new WordDetailsViewModel.Factory(wordDetailsInteractor);
    }
}
